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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Mapper
public interface DoctorMapperHelper {

    @Named("noScheduleDayDoctor")
    @Mapping(target = "scheduleDays", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutScheduleDays(Doctor doctor);

    @Named("noSpecializationsInDoctor")
    @Mapping(target = "specializations", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutSpecialization(Doctor doctor);

    @IterableMapping(qualifiedByName = "noDoctorAppointment")
    List<AppointmentDTO> getAppointmentDTOListWithoutDoctor(Collection<Appointment> appointments);
//    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);

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
    @Mappings({
            @Mapping(target = "doctor", ignore = true),
            @Mapping(target = "animal", ignore = true),
            @Mapping(target = "owner", ignore = true),
            @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToString")
    })
    AppointmentDTO convertToAppointmentDTOWithoutDoctor(Appointment appointment);

    @Named("dateTimeConversionToString")
    default String convertToString(LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(localDateTime.toLocalDate()));
        LocalTime localTime = localDateTime.toLocalTime();
        stringBuilder.append(" ").append(localTime.getHour());
        int minutes = localTime.getMinute();
        stringBuilder.append(":").append(minutes == 0 ? "00" : minutes);
        return stringBuilder.toString();
    }

    @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToLocalDateTime")
    Appointment convertToAppointmentWithConversionToLocalDateTime(AppointmentDTO appointmentDTO);

    @Named("dateTimeConversionToLocalDateTime")
    default LocalDateTime convertLocalDateTime(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }

    @Named("medServiceEntitiesWithoutSpecialization")
    @IterableMapping(qualifiedByName = "medServiceEntityWithoutSpecialization")
    List<MedicalServiceEntityDTO> getMedicalServiceEntitiesDTOWithoutSpecialization(Collection<MedicalServiceEntity> medicalServiceEntities);

    @Named("medServiceEntityWithoutSpecialization")
    @Mapping(target = "specialization", ignore = true)
    MedicalServiceEntityDTO getMedicalServiceEntitiesDTOWithoutSpecialization(MedicalServiceEntity medicalServiceEntity);
}
