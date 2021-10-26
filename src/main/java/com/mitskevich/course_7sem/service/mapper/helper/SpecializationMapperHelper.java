package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

//Дописать остальные методы для конвертации в DTO и обратно
@Mapper
public interface SpecializationMapperHelper {
    @Named("noDoctorSpecialization")
    @Mapping(target = "doctors", ignore = true)
    SpecializationDTO convertToSpecializationDTOWithoutDoctors(Specialization specialization);
}
