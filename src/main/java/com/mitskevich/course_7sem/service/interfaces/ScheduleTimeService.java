package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ScheduleTimeService {
    ScheduleTime findById(UUID scheduleTimeId);
    List<ScheduleTime> getScheduleTimeByScheduleDayId(UUID scheduleDayId);
    List<ScheduleTime> createScheduleTime(ScheduleDay scheduleDay);
    List<ScheduleTime> updateScheduleTime();
    ScheduleTime saveScheduleTime(ScheduleTime scheduleTime);
    void deleteScheduleTime(ScheduleDay scheduleDay);
    void blockTime(UUID scheduleTimeId);
    ScheduleTime findByScheduleDayAndTime(UUID scheduleDayId, LocalTime time);
}
