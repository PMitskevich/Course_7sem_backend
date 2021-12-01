package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.ScheduleDay;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleDayService {
    ScheduleDay findById(UUID scheduleDayId);
    List<ScheduleDay> getScheduleByDoctorId(UUID doctorId);
    List<ScheduleDay> createSchedule(Doctor doctor);
    List<ScheduleDay> updateSchedule(Doctor doctor);
    ScheduleDay updateSchedule(ScheduleDay scheduleDay);
    void deleteSchedule(UUID doctorId);
    ScheduleDay findByDate(LocalDate date, UUID doctorId);
    boolean isAllScheduleTimesBlocked(UUID scheduleDayId);
}
