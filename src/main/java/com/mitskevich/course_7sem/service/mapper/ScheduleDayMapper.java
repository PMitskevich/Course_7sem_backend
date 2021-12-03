package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.ScheduleDayMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(uses = {ScheduleDayMapperHelper.class, DoctorMapperHelper.class, ScheduleTimeMapper.class})
public interface ScheduleDayMapper {
    @Named("scheduleDayDTO")
    @Mappings({
            @Mapping(target = "doctor", qualifiedByName = "noScheduleDayDoctor"),
            @Mapping(target = "scheduleTimes", qualifiedByName = "scheduleTimeDTO")
    })
    ScheduleDayDTO convertToScheduleDayDTO(ScheduleDay scheduleDay);
    ScheduleDay convertToScheduleDay(ScheduleDayDTO scheduleDay);
}
