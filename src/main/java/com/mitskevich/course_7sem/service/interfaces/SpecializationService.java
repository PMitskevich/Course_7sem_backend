package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Specialization;

import java.util.List;
import java.util.UUID;

public interface SpecializationService {
    Specialization saveSpecialization(Specialization specialization);
    Specialization saveDraftSpecialization(UUID specializationId);
    Specialization updateSpecialization(UUID uuid, Specialization specialization);
    void deleteById(UUID uuid);
    Specialization findById(UUID uuid);
    List<Specialization> findAll();
}
