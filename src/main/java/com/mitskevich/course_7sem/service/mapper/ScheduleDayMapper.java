package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleDayMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ScheduleDayMapperHelper.class, DoctorMapperHelper.class})
public interface ScheduleDayMapper {
    @Mapping(target = "doctor", qualifiedByName = "noScheduleDayDoctor")
    ScheduleDayDTO convertToScheduleDayDTO(ScheduleDay scheduleDay);
    ScheduleDay convertToScheduleDay(ScheduleDayDTO scheduleDay);
}
