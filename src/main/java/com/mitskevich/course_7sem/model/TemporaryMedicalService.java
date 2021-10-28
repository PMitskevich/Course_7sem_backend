package com.mitskevich.course_7sem.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
public class TemporaryMedicalService {
    private BigInteger specializationId;
    private String name;
    private String price;

    public TemporaryMedicalService() {}

    public TemporaryMedicalService(BigInteger specializationId) {
        this.specializationId = specializationId;
    }
}
