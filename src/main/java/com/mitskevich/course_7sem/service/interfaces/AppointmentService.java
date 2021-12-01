package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    Appointment findById(UUID appointmentId);
    Appointment saveAppointment(Appointment appointment);
    Appointment saveDraftAppointment(UUID scheduleDayId, UUID scheduleTimeId, UUID doctorId, UUID userId);
    Appointment updateAppointment(Appointment appointment, UUID appointmentId);
    void deleteAppointment(UUID appointmentId);
    void preDeleteOperations(UUID appointmentId);
    List<Appointment> findAll();
    List<Appointment> findByOwnerId(UUID ownerId);
}
