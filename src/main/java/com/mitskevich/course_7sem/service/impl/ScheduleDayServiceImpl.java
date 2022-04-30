package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.repository.ScheduleDayRepository;
import com.mitskevich.course_7sem.service.interfaces.ScheduleDayService;
import com.mitskevich.course_7sem.service.interfaces.ScheduleTimeService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleDayServiceImpl implements ScheduleDayService {

    private final ScheduleDayRepository scheduleDayRepository;
    private final ScheduleTimeService scheduleTimeService;
    private final MessageSource messageSource;

    public ScheduleDayServiceImpl(ScheduleDayRepository scheduleDayRepository, ScheduleTimeService scheduleTimeService, MessageSource messageSource) {
        this.scheduleDayRepository = scheduleDayRepository;
        this.scheduleTimeService = scheduleTimeService;
        this.messageSource = messageSource;
    }

    @Override
    public ScheduleDay findById(UUID scheduleDayId) {
        return scheduleDayRepository.findById(scheduleDayId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{scheduleDayId, messageSource.getMessage("entity.ScheduleDay", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<ScheduleDay> getScheduleByDoctorId(UUID doctorId) {
        return scheduleDayRepository.getByDoctorId(doctorId);
    }

    @Override
    public List<ScheduleDay> createSchedule(Doctor doctor) {
        List<ScheduleDay> scheduleDays = new ArrayList<>();
        ScheduleDay scheduleDay = new ScheduleDay(LocalDate.now());
        scheduleDay.setDoctor(doctor);
        ScheduleDay persistedScheduleDay = scheduleDayRepository.save(scheduleDay);
        persistedScheduleDay.setScheduleTimes(scheduleTimeService.createScheduleTime(persistedScheduleDay));
        scheduleDays.add(persistedScheduleDay);
        for (int i = 0; i < 13; i++) {
            LocalDate currentDate = scheduleDays.get(i).getDate();
            ScheduleDay nextScheduleDay = new ScheduleDay(currentDate.plusDays(1));
            nextScheduleDay.setDoctor(doctor);
            ScheduleDay nextPersistedScheduleDay = scheduleDayRepository.save(nextScheduleDay);
            nextPersistedScheduleDay.setScheduleTimes(scheduleTimeService.createScheduleTime(nextPersistedScheduleDay));
            scheduleDays.add(nextPersistedScheduleDay);
        }
        return scheduleDays;
    }

    @Override
    public List<ScheduleDay> updateSchedule(Doctor doctor) {
        List<ScheduleDay> scheduleDays = doctor.getScheduleDays();
        LocalDate currentDate = LocalDate.now();
        Iterator<ScheduleDay> scheduleDayIterator = scheduleDays.iterator();
        while (scheduleDayIterator.hasNext()) {
            ScheduleDay currentScheduleDay = scheduleDayIterator.next();
            if (currentScheduleDay.getDate().compareTo(currentDate) < 0) {
                scheduleDayIterator.remove();
                scheduleDayRepository.deleteById(currentScheduleDay.getId());
            }
        }
        if (scheduleDays.size() < 14) {
            addExtraScheduleDays(doctor, scheduleDays, 14 - scheduleDays.size());
        }
        return scheduleDays;
    }

    @Override
    public ScheduleDay updateSchedule(ScheduleDay scheduleDay) {
        return scheduleDayRepository.save(scheduleDay);
    }

    @Override
    public void deleteSchedule(UUID doctorId) {

    }

    @Override
    public ScheduleDay findByDate(LocalDate date, UUID doctorId) {
        return scheduleDayRepository.getByDateAndDoctorId(date, doctorId);
    }

    @Override
    public boolean isAllScheduleTimesBlocked(UUID scheduleDayId) {
        return scheduleTimeService.getAvailableScheduleTimes(scheduleDayId).size() == 0;
    }

    @Override
    public List<ScheduleDay> getActualDays(List<ScheduleDay> days) {
//        List<ScheduleDay> expiredDates = new ArrayList<>();
        Iterator<ScheduleDay> iterator = days.iterator();
        while (iterator.hasNext()) {
            ScheduleDay day = iterator.next();
            if (day.getDate().compareTo(LocalDate.now()) < 0) {
//                expiredDates.add(day);
                iterator.remove();
                scheduleDayRepository.deleteById(day.getId());
            }
        }
//        for (ScheduleDay day : days) {
//            if (day.getDate().compareTo(LocalDate.now()) < 0) {
//                days.remove(day);
//                scheduleDayRepository.deleteById(day.getId());
//            }
//        }

//        deleteAllExpiredDates(expiredDates);
//        days.removeAll(expiredDates);
        return days;
    }

    @Override
    public ScheduleDay createScheduleDay(Doctor doctor, LocalDate date) {
        ScheduleDay scheduleDay = new ScheduleDay(date);
        scheduleDay.setDoctor(doctor);
        ScheduleDay persistedScheduleDay = scheduleDayRepository.save(scheduleDay);
        persistedScheduleDay.setScheduleTimes(scheduleTimeService.createScheduleTime(persistedScheduleDay));
        return scheduleDay;
    }

    private void addExtraScheduleDays(Doctor doctor, List<ScheduleDay> scheduleDays, int numberOfExtraDays) {
        LocalDate oldMaxDate = findMaxLocalDate(scheduleDays);
        for (int i = 0; i < numberOfExtraDays; i++) {
            LocalDate currentMaxDate = oldMaxDate.plusDays(1);
            oldMaxDate = currentMaxDate;
            ScheduleDay anotherScheduleDay = new ScheduleDay();
            anotherScheduleDay.setDate(currentMaxDate);
            anotherScheduleDay.setDoctor(doctor);
            ScheduleDay persistedScheduleDay = scheduleDayRepository.save(anotherScheduleDay);
            persistedScheduleDay.setScheduleTimes(scheduleTimeService.createScheduleTime(persistedScheduleDay));
            scheduleDays.add(persistedScheduleDay);
        }
    }

    private LocalDate findMaxLocalDate(List<ScheduleDay> scheduleDays) {
        LocalDate maxDate = scheduleDays.get(0).getDate();
        for (int i = 1; i < scheduleDays.size(); i++) {
            LocalDate currentDate = scheduleDays.get(i).getDate();
            if (currentDate.compareTo(maxDate) > 0) {
                maxDate = currentDate;
            }
        }
        return maxDate;
    }

    private void deleteAllExpiredDates(List<ScheduleDay> expiredDates) {
        for (ScheduleDay day: expiredDates) {
            scheduleTimeService.deleteScheduleTime(day);
            scheduleDayRepository.delete(day);
        }
    }
}
