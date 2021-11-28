package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.MedicalServiceEntityDTO;
import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.dto.ScheduleTimeDTO;
import com.mitskevich.course_7sem.dto.SpecializationDTO;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.model.ScheduleTime;
import com.mitskevich.course_7sem.model.Specialization;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DoctorMapperHelper {
    @Named("noAppointmentDoctor")
    @Mapping(target = "appointments", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutAppointment(Doctor doctor);

    @Named("noScheduleDayDoctor")
    @Mapping(target = "scheduleDays", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutScheduleDays(Doctor doctor);

    @Named("noSpecializationsInDoctor")
    @Mapping(target = "specializations", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutSpecialization(Doctor doctor);

    @IterableMapping(qualifiedByName = "noDoctorAppointment")
    List<AppointmentDTO> getAppointmentDTOListWithoutDoctor(Collection<Appointment> appointments);
    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);

    @IterableMapping(qualifiedByName = "noDoctorSpecializations")
    List<SpecializationDTO> getSpecializationDTOListWithoutDoctor(Collection<Specialization> specializations);
    List<Specialization> getSpecializationList(Collection<SpecializationDTO> specializations);

    @IterableMapping(qualifiedByName = "noDoctorScheduleDays")
    List<ScheduleDayDTO> getScheduleDayDTOListWithoutDoctor(Collection<ScheduleDay> scheduleDays);
    List<ScheduleDay> getScheduleDayList(Collection<ScheduleDayDTO> scheduleDays);

    @Named("noDoctorScheduleDays")
    @Mappings({
            @Mapping(target = "doctor", ignore = true),
            @Mapping(target = "scheduleTimes", qualifiedByName = "scheduleTimesWithoutDays")
    })
    ScheduleDayDTO convertToScheduleDayDTOWithoutDoctor(ScheduleDay scheduleDay);

    @Named("scheduleTimesWithoutDays")
    @IterableMapping(qualifiedByName = "scheduleTimeWithoutDays")
    List<ScheduleTimeDTO> getScheduleTimesDTOWithoutScheduleDays(Collection<ScheduleTime> scheduleTimes);

    @Named("scheduleTimeWithoutDays")
    @Mapping(target = "scheduleDay", ignore = true)
    ScheduleTimeDTO getScheduleTimeWithoutScheduleDays(ScheduleTime scheduleTime);

    @Named("noDoctorSpecializations")
    @Mappings({
            @Mapping(target = "doctors", ignore = true),
            @Mapping(target = "medicalServiceEntities", qualifiedByName = "medServiceEntitiesWithoutSpecialization")
    })
    SpecializationDTO convertToSpecializationDTOWithoutDoctors(Specialization specialization);

    @Named("noDoctorAppointment")
    @Mapping(target = "doctor", ignore = true)
    AppointmentDTO convertToAppointmentDTOWithoutDoctor(Appointment appointment);

    @Named("medServiceEntitiesWithoutSpecialization")
    @IterableMapping(qualifiedByName = "medServiceEntityWithoutSpecialization")
    List<MedicalServiceEntityDTO> getMedicalServiceEntitiesDTOWithoutSpecialization(Collection<MedicalServiceEntity> medicalServiceEntities);

    @Named("medServiceEntityWithoutSpecialization")
    @Mapping(target = "specialization", ignore = true)
    MedicalServiceEntityDTO getMedicalServiceEntitiesDTOWithoutSpecialization(MedicalServiceEntity medicalServiceEntity);
}
