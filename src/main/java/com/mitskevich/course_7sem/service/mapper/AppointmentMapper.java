package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.service.mapper.helper.AnimalMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.AppointmentMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.OwnerMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(uses = {AnimalMapperHelper.class})
public interface AppointmentMapper {
    @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToLocalDateTime")
    Appointment convertToAppointment(AppointmentDTO appointmentDTO);

    @Mappings({
            @Mapping(target = "doctor", qualifiedByName = "noAppointmentDoctor"),
            @Mapping(target = "owner", qualifiedByName = "noAppointmentOwner"),
            @Mapping(target = "animal", qualifiedByName = "noAppointmentAnimal"),
            @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToString")
//            @Mapping(target = "dateTime", expression = "java(LocalDateTime.of(appointmentDTO.getDateTime().getYear(), " +
//                    "appointmentDTO.getDateTime().getMonth(), appointmentDTO.getDateTime().getDayOfMonth(), " +
//                    "appointmentDTO.getDateTime().getHour(), appointmentDTO.getDateTime().getMinute()))")
    })
    AppointmentDTO convertToAppointmentDTO(Appointment appointment);

    @Named("dateTimeConversionToLocalDateTime")
    default LocalDateTime convertLocalDateTime(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }

    @Named("dateTimeConversionToString")
    default String convertToString(LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(localDateTime.toLocalDate()));
        LocalTime localTime = localDateTime.toLocalTime();
        stringBuilder.append(" ").append(localTime.getHour());
        int minutes = localTime.getMinute();
        stringBuilder.append(":").append(minutes == 0 ? "00" : minutes);
        return stringBuilder.toString();
    }

    @Named("noAppointmentDoctor")
    @Mapping(target = "appointments", ignore = true)
    DoctorDTO convertToDoctorDTOWithoutAppointment(Doctor doctor);

    @Named("noAppointmentOwner")
    @Mapping(target = "appointments", ignore = true)
    OwnerDTO convertToOwnerDTOWithoutAppointments(Owner owner);
}
