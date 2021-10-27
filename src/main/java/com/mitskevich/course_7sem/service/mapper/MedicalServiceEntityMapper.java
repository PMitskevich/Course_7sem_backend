package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.MedicalServiceEntityDTO;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import com.mitskevich.course_7sem.service.mapper.helper.MedicalServiceEntityMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.SpecializationMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MedicalServiceEntityMapperHelper.class, SpecializationMapperHelper.class})
public interface MedicalServiceEntityMapper {
    @Mapping(target = "specialization", qualifiedByName = "noMedicalServiceEntityInSpecialization")
    MedicalServiceEntityDTO convertToMedicalServiceEntity(MedicalServiceEntity medicalServiceEntity);
    MedicalServiceEntity convertToMedicalServiceEntity(MedicalServiceEntityDTO medicalServiceEntity);
}
