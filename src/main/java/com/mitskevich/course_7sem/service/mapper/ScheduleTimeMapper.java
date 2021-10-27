package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.ScheduleTimeDTO;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleDayMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleTimeMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ScheduleTimeMapperHelper.class, ScheduleDayMapperHelper.class})
public interface ScheduleTimeMapper {
    @Mapping(target = "scheduleDay", qualifiedByName = "noScheduleTimeInScheduleDay")
    ScheduleTimeDTO convertToScheduleTimeDTO(ScheduleTime scheduleTime);
    ScheduleTime convertToScheduleTime(ScheduleTimeDTO scheduleTime);
}
