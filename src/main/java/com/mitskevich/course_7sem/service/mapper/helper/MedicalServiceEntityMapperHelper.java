package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.MedicalServiceEntityDTO;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface MedicalServiceEntityMapperHelper {
    @Named("noSpecializationInMedicalServiceEntity")
    @Mapping(target = "specialization", ignore = true)
    MedicalServiceEntityDTO convertToMedicalServiceEntityDTOWithoutSpecialization(MedicalServiceEntity medicalServiceEntity);
}
