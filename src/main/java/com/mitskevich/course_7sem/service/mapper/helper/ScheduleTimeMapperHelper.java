package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.ScheduleTimeDTO;
import com.mitskevich.course_7sem.model.ScheduleTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ScheduleTimeMapperHelper {
    @Named("noScheduleDaysInScheduleTimes")
    @Mapping(target = "scheduleDay", ignore = true)
    ScheduleTimeDTO convertToScheduleTimeWithoutScheduleDate(ScheduleTime scheduleTime);
}
