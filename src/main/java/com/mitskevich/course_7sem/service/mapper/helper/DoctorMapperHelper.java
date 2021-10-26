package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DoctorMapperHelper {
    @Named("noAppointmentDoctor")
    @Mapping(target = "appointments", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutAppointment(Doctor doctor);

    @Mapping(target = "appointments", qualifiedByName = "noDoctorAppointment")
    List<AppointmentDTO> getAppointmentDTOListWithoutDoctor(Collection<Appointment> appointments);
    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);

    @Mapping(target = "specializations", qualifiedByName = "noDoctorSpecialization")
    List<SpecializationDTO> getSpecializationDTOListWithoutDoctor(Collection<Specialization> specializations);
    List<Specialization> getSpecializationList(Collection<SpecializationDTO> specializations);

    @Mapping(target = "scheduleDays", qualifiedByName = "noDoctorScheduleDays")
    List<ScheduleDayDTO> getScheduleDayDTOListWithoutDoctor(Collection<ScheduleDay> scheduleDays);
    List<ScheduleDay> getScheduleDayList(Collection<ScheduleDayDTO> scheduleDays);
}
