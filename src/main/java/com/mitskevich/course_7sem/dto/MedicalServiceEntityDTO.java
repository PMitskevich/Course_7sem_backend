package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@NoArgsConstructor
@Data
public class MedicalServiceEntityDTO {
    private UUID id;
    private String name;
    private String price;
    private SpecializationDTO specialization;
}
