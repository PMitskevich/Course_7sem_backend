package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.service.mapper.helper.MedicalServiceEntityMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.SpecializationMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {SpecializationMapperHelper.class, MedicalServiceEntityMapperHelper.class})
public interface SpecializationMapper {
    @Mapping(target = "medicalServiceEntities", qualifiedByName = "noSpecializationInMedicalServiceEntity")
    SpecializationDTO convertToSpecializationDTO(Specialization specialization);
    Specialization convertToSpecialization(SpecializationDTO specialization);
}
