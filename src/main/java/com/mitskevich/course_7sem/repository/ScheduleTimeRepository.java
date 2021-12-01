package com.mitskevich.course_7sem.repository;

import com.mitskevich.course_7sem.model.ScheduleTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, UUID> {
    List<ScheduleTime> findByScheduleDayId(UUID id);
    ScheduleTime findByScheduleDayIdAndTime(UUID scheduleDayId, LocalTime time);
    List<ScheduleTime> findScheduleTimeByIsBlockedAndScheduleDayId(Boolean isBlocked, UUID scheduleDayId);
}
