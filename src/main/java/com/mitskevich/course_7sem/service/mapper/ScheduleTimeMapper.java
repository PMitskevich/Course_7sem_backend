package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.dto.ScheduleTimeDTO;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleDayMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleTimeMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(uses = {ScheduleTimeMapperHelper.class, ScheduleDayMapperHelper.class})
public interface ScheduleTimeMapper {
    @Named("scheduleTimeDTO")
    @Mapping(target = "scheduleDay", qualifiedByName = "noScheduleTimeInScheduleDay")
    ScheduleTimeDTO convertToScheduleTimeDTO(ScheduleTime scheduleTime);
    ScheduleTime convertToScheduleTime(ScheduleTimeDTO scheduleTime);

    @Named("noScheduleTimeInScheduleDay")
    @Mappings({
            @Mapping(target = "scheduleTimes", ignore = true),
            @Mapping(target = "doctor", ignore = true)
    })
    ScheduleDayDTO convertToScheduleDayDTOWithoutScheduleTimes(ScheduleDay scheduleDay);
}
