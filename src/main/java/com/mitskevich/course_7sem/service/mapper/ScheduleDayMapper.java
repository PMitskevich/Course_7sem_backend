package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleDayMapperHelper;
import org.mapstruct.Mapper;

@Mapper(uses = {ScheduleDayMapperHelper.class})
public interface ScheduleDayMapper {
    ScheduleDayDTO convertToScheduleDayDTO(ScheduleDay scheduleDay);
    ScheduleDay convertToScheduleDay(ScheduleDayDTO scheduleDay);
}
