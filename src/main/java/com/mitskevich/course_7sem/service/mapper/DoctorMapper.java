package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleDayMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {DoctorMapperHelper.class, ScheduleDayMapperHelper.class, ScheduleDayMapper.class})
public interface DoctorMapper {
    Doctor convertToDoctor(DoctorDTO doctor);
    @Mapping(target = "scheduleDays", qualifiedByName = "scheduleDayDTO")
//    @Mapping(target = "scheduleDays", qualifiedByName = "noDoctorScheduleDays")
    DoctorDTO convertToDoctorDTO(Doctor doctor);
}
