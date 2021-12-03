package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.dto.ScheduleDayDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.ScheduleDay;
import com.mitskevich.course_7sem.service.mapper.ScheduleDayMapper;
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
public interface OwnerMapperHelper {
    @Named("noAnimalsOwner")
    @Mapping(target = "animals", ignore = true)
    OwnerDTO convertToOwnerDTOWithoutAnimals(Owner owner);

    @Named("noUserOwner")
    @Mapping(target = "user", ignore = true)
    OwnerDTO convertOwnerDTOWithoutUser(Owner owner);

    @Named("noAppointmentOwner")
    @Mapping(target = "appointments", ignore = true)
    OwnerDTO convertToOwnerDTOWithoutAppointments(Owner owner);

    @IterableMapping(qualifiedByName = "noOwnerAnimals")
    List<AnimalDTO> getAnimalDTOList(Collection<Animal> animals);
    @IterableMapping(qualifiedByName = "animalsWithParsedAppointmentDates")
    List<Animal> getAnimalList(Collection<AnimalDTO> animals);

    @Named("animalsWithParsedAppointmentDates")
    Animal convertAnimalDTOtoEntity(AnimalDTO animalDTO);

    @IterableMapping(qualifiedByName = "appointmentWithParsedDate")
    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);

    @Named("appointmentWithParsedDate")
    @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToLocalDateTimeForOwner")
    Appointment convertAppointmentDTOtoEntity(AppointmentDTO appointmentDTO);

    @Named("noOwnerAnimals")
    @Mapping(target = "owner", ignore = true)
    AnimalDTO convertToAnimalDTOWithoutOwner(Animal animal);

    @IterableMapping(qualifiedByName = "noOwnerAppointments")
    List<AppointmentDTO> getAppointmentDTOList(Collection<Appointment> appointments);

    @Named("noOwnerAppointments")
    @Mappings({
            @Mapping(target = "owner", ignore = true),
            @Mapping(target = "doctor", ignore = true),
            @Mapping(target = "animal", ignore = true),
            @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToStringForOwner")
    })
    AppointmentDTO convertToAppointmentDTOWithoutOwner(Appointment appointment);

    @Named("dateTimeConversionToStringForOwner")
    default String convertToString(LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(localDateTime.toLocalDate()));
        LocalTime localTime = localDateTime.toLocalTime();
        stringBuilder.append(" ").append(localTime.getHour());
        int minutes = localTime.getMinute();
        stringBuilder.append(":").append(minutes == 0 ? "00" : minutes);
        return stringBuilder.toString();
    }

    @Named("dateTimeConversionToLocalDateTimeForOwner")
    default LocalDateTime convertLocalDateTime(String localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(localDateTime, formatter);
    }

//    Appointment convertToAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO convertToAppointmentDTO(Appointment appointment);
}
