package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.MedicalServiceEntity;

import java.util.List;
import java.util.UUID;

public interface MedicalService {
    MedicalServiceEntity findById(UUID id);
    List<MedicalServiceEntity> findAll();
    List<MedicalServiceEntity> findBySpecializationId(UUID specializationId);
    MedicalServiceEntity updateMedicalService(MedicalServiceEntity medicalService, UUID uuid);
    void deleteMedicalService(UUID id);
//    void updateMedicalServiceEntities(List<MedicalServiceEntity> serviceEntities, UUID specializationId);
}
