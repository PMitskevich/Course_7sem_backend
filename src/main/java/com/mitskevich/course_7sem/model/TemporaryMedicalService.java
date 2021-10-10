package com.mitskevich.course_7sem.model;

import lombok.Data;

import java.util.UUID;

@Data
public class TemporaryMedicalService {
    private UUID specializationId;
    private String name;
    private String price;

    public TemporaryMedicalService() {}

    public TemporaryMedicalService(UUID specializationId) {
        this.specializationId = specializationId;
    }
}
