package com.mitskevich.course_7sem.repository;

import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalServiceEntity, UUID> {
    List<MedicalServiceEntity> findBySpecializationId(UUID specializationId);
}
