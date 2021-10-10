package com.mitskevich.course_7sem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChangedServices {
    private List<MedicalServiceEntity> medicalServiceEntities;
    private UUID specializationId;
}
