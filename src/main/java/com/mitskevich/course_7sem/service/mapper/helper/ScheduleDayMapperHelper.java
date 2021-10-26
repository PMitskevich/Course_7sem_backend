package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.model.ScheduleDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

//Дописать остальные методы для конвертации в DTO и обратно
@Mapper
public interface ScheduleDayMapperHelper {
    @Named("noDoctorScheduleDays")
    @Mapping(target = "doctor", ignore = true)
    ScheduleDayDTO convertToScheduleDayDTOWithoutDoctor(ScheduleDay scheduleDay);
}
