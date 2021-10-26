package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.service.mapper.helper.SpecializationMapperHelper;
import org.mapstruct.Mapper;

@Mapper(uses = {SpecializationMapperHelper.class})
public interface SpecializationMapper {
    SpecializationDTO convertToSpecializationDTO(Specialization specialization);
    Specialization convertToSpecialization(SpecializationDTO specialization);
}
