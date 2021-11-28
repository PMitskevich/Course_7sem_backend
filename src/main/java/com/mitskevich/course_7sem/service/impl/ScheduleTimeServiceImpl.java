package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.repository.ScheduleTimeRepository;
import com.mitskevich.course_7sem.service.interfaces.ScheduleTimeService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduleTimeServiceImpl implements ScheduleTimeService {

    private final ScheduleTimeRepository scheduleTimeRepository;
    private final MessageSource messageSource;

    public ScheduleTimeServiceImpl(ScheduleTimeRepository scheduleTimeRepository, MessageSource messageSource) {
        this.scheduleTimeRepository = scheduleTimeRepository;
        this.messageSource = messageSource;
    }

    @Override
    public ScheduleTime findById(UUID scheduleTimeId) {
        return scheduleTimeRepository.findById(scheduleTimeId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{scheduleTimeId, messageSource.getMessage("entity.ScheduleTime", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<ScheduleTime> getScheduleTimeByScheduleDayId(UUID scheduleDayId) {
        return scheduleTimeRepository.findByScheduleDayId(scheduleDayId);
    }

    @Override
    public List<ScheduleTime> createScheduleTime(ScheduleDay scheduleDay) {
        List<ScheduleTime> scheduleTimes = new ArrayList<>();
        ScheduleTime scheduleTime = new ScheduleTime();
        LocalTime currentTime = LocalTime.parse("10:00");
        scheduleTime.setTime(currentTime);
        scheduleTime.setScheduleDay(scheduleDay);
        ScheduleTime persistedScheduleTime = scheduleTimeRepository.save(scheduleTime);
        scheduleTimes.add(persistedScheduleTime);
        while (currentTime.plusMinutes(30).compareTo(LocalTime.parse("18:00")) < 0) {
            ScheduleTime nextScheduleTime = new ScheduleTime();
            LocalTime nextTime = currentTime.plusMinutes(30);
            if (nextTime.compareTo(LocalTime.parse("13:00")) >= 0 && nextTime.compareTo(LocalTime.parse("14:00")) < 0) {
                currentTime = LocalTime.parse("14:00");
            }
            else {
                currentTime = nextTime;
            }
            nextScheduleTime.setTime(currentTime);
            nextScheduleTime.setScheduleDay(scheduleDay);
            ScheduleTime nextPersistedScheduleTime = scheduleTimeRepository.save(nextScheduleTime);
            scheduleTimes.add(nextPersistedScheduleTime);
        }
        return scheduleTimes;
    }

    @Override
    public List<ScheduleTime> updateScheduleTime() {
        return null;
    }

    @Override
    public ScheduleTime saveScheduleTime(ScheduleTime scheduleTime) {
        return scheduleTimeRepository.save(scheduleTime);
    }

    @Override
    public void deleteScheduleTime(ScheduleDay scheduleDay) {
        List<ScheduleTime> scheduleTimes = scheduleTimeRepository.findByScheduleDayId(scheduleDay.getId());
        for (ScheduleTime scheduleTime: scheduleTimes) {
            scheduleTimeRepository.delete(scheduleTime);
        }
    }

    @Override
    public void blockTime(UUID scheduleTimeId) {
        ScheduleTime chosenTime = findById(scheduleTimeId);
        chosenTime.setIsBlocked(true);
        scheduleTimeRepository.save(chosenTime);
    }

    @Override
    public ScheduleTime findByScheduleDayAndTime(UUID scheduleDayId, LocalTime time) {
        return scheduleTimeRepository.findByScheduleDayIdAndTime(scheduleDayId, time);
    }
}
