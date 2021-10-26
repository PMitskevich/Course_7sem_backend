package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import org.mapstruct.Mapper;

@Mapper(uses = {DoctorMapperHelper.class})
public interface DoctorMapper {
    Doctor convertToDoctor(DoctorDTO doctor);
    DoctorDTO convertToDoctorDTO(Doctor doctor);
}
