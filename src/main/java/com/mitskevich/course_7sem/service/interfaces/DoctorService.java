package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Doctor findById(UUID id);
    List<Doctor> findAll();
//    Doctor saveDoctor(Doctor doctor, UUID[] specializationIds);
    Doctor saveDoctor(Doctor doctor);
//    Doctor updateDoctor(Doctor doctor, UUID id, UUID[] specializationIds);
    Doctor updateDoctor(Doctor doctor, UUID id);
    void deleteDoctor(UUID id);
}
