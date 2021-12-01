package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.repository.AppointmentRepository;
import com.mitskevich.course_7sem.service.interfaces.AnimalService;
import com.mitskevich.course_7sem.service.interfaces.AppointmentService;
import com.mitskevich.course_7sem.service.interfaces.DoctorService;
import com.mitskevich.course_7sem.service.interfaces.OwnerService;
import com.mitskevich.course_7sem.service.interfaces.ScheduleDayService;
import com.mitskevich.course_7sem.service.interfaces.ScheduleTimeService;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleDayService scheduleDayService;
    private final ScheduleTimeService scheduleTimeService;
    private final DoctorService doctorService;
    private final UserService userService;
    private final AnimalService animalService;
    private final OwnerService ownerService;
    private final MessageSource messageSource;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ScheduleDayService scheduleDayService, ScheduleTimeService scheduleTimeService, DoctorService doctorService, UserService userService, AnimalService animalService, OwnerService ownerService, MessageSource messageSource) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleDayService = scheduleDayService;
        this.scheduleTimeService = scheduleTimeService;
        this.doctorService = doctorService;
        this.userService = userService;
        this.animalService = animalService;
        this.ownerService = ownerService;
        this.messageSource = messageSource;
    }

    @Override
    public Appointment findById(UUID appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{appointmentId, messageSource.getMessage("entity.Appointment", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        Animal animal = appointment.getAnimal();
        if (animal.getId() == null) {
            Owner owner = appointment.getOwner();
            animal.setOwner(owner);
            Animal persistedAnimal = animalService.saveAnimal(animal);
            appointment.setAnimal(persistedAnimal);
        }
        else {
            Animal existedAnimal = animalService.findById(animal.getId());
            existedAnimal.getAppointments().add(appointment);
            appointment.setAnimal(animal);
        }
        changeScheduleTime(appointment, appointment.getDateTime(), true);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment saveDraftAppointment(UUID scheduleDayId, UUID scheduleTimeId, UUID doctorId, UUID userId) {
        ScheduleDay scheduleDay = scheduleDayService.findById(scheduleDayId);
        ScheduleTime scheduleTime = scheduleTimeService.findById(scheduleTimeId);
        Appointment appointment = Appointment.of(scheduleDay, scheduleTime);
        Doctor doctor = doctorService.findById(doctorId);
        User user = userService.findById(userId);
        appointment.setDoctor(doctor);
        appointment.setOwner(user.getOwner());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, UUID appointmentId) {
        return null;
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public void preDeleteOperations(UUID appointmentId) {
        Appointment appointment = findById(appointmentId);
        LocalDateTime dateTime = appointment.getDateTime();
        changeScheduleTime(appointment, dateTime, false);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findByOwnerId(UUID ownerId) {
        return appointmentRepository.findByOwnerId(ownerId);
    }

    private void changeScheduleTime(Appointment appointment, LocalDateTime dateTime, boolean shouldBLock) {
        LocalDate date = fetchLocalDateFromLocalDateTime(dateTime);
        LocalTime time = fetchLocalTimeFromLocalDateTime(dateTime);
        ScheduleDay scheduleDay = scheduleDayService.findByDate(date, appointment.getDoctor().getId());
        if (scheduleDay != null) {
            ScheduleTime scheduleTime = scheduleTimeService.findByScheduleDayAndTime(scheduleDay.getId(), time)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND, messageSource.getMessage("message.ResourceNotFound",
                                    new Object[]{date,
                                            messageSource.getMessage("criterion.date", null, LocaleContextHolder.getLocale()),
                                            messageSource.getMessage("entity.ScheduleTime", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
            scheduleTime.setIsBlocked(shouldBLock);
            scheduleTimeService.saveScheduleTime(scheduleTime);
            if (scheduleDayService.isAllScheduleTimesBlocked(scheduleDay.getId()) && shouldBLock) {
                scheduleDay.setIsBlocked(true);
                scheduleDayService.updateSchedule(scheduleDay);
            }
        }
    }

    private LocalDate fetchLocalDateFromLocalDateTime(LocalDateTime dateTime) {
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        return LocalDate.of(year, month, day);
    }

    private LocalTime fetchLocalTimeFromLocalDateTime(LocalDateTime dateTime) {
        int hours = dateTime.getHour();
        int minutes = dateTime.getMinute();
        return LocalTime.of(hours, minutes);
    }
}
