package com.mitskevich.course_7sem.repository;

import com.mitskevich.course_7sem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByOwnerId(UUID ownerId);
}
