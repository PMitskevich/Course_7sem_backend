package com.mitskevich.course_7sem.repository;

import com.mitskevich.course_7sem.model.ScheduleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleDayRepository extends JpaRepository<ScheduleDay, UUID> {
    List<ScheduleDay> getByDoctorId(UUID doctorId);
    ScheduleDay getByDateAndDoctorId(LocalDate date, UUID doctorId);
}
