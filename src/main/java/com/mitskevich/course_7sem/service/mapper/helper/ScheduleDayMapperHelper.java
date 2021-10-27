package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.dto.ScheduleTimeDTO;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

//Дописать остальные методы для конвертации в DTO и обратно
@Mapper
public interface ScheduleDayMapperHelper {
    @Named("noDoctorScheduleDays")
    @Mapping(target = "doctor", ignore = true)
    ScheduleDayDTO convertToScheduleDayDTOWithoutDoctor(ScheduleDay scheduleDay);

    @Named("noScheduleTimeInScheduleDay")
    @Mapping(target = "scheduleTimes", ignore = true)
    ScheduleDayDTO convertToScheduleDayDTOWithoutScheduleTimes(ScheduleDay scheduleDay);

    @Mapping(target = "scheduleTimes", qualifiedByName = "noScheduleDaysInScheduleTimes")
    List<ScheduleTimeDTO> getScheduleTimeDTOList(Collection<ScheduleTime> scheduleTimes);
    List<ScheduleTime> getScheduleTimeList(Collection<ScheduleTimeDTO> scheduleTimes);

    DoctorDTO convertToDoctorDTO(Doctor doctor);
    Doctor convertToDoctor(DoctorDTO doctor);
}
