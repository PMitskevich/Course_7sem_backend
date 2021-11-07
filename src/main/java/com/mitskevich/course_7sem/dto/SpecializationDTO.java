package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class SpecializationDTO {
    private UUID id;
    private String name;
    private String description;
    private List<DoctorDTO> doctors;
    private List<MedicalServiceEntityDTO> medicalServiceEntities;
}
