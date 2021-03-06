package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.dto.UserDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Mapper
public interface AnimalMapperHelper {
    @Named("noAppointmentAnimal")
    @Mappings({
            @Mapping(target = "owner", qualifiedByName = "noUserOwner"),
            @Mapping(target = "appointments", ignore = true)
    })
    AnimalDTO convertToAnimalDTOWithoutAppointments(Animal animal);

    @IterableMapping(qualifiedByName = "noAnimalAppointment")
    List<AppointmentDTO> getAppointmentDTOList(Collection<Appointment> appointments);
//    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);

    @Named("noUserOwner")
    @Mappings({
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "appointments", ignore = true),
            @Mapping(target = "animals", ignore = true)
    })
    OwnerDTO convertOwnerDTOWithoutUser(Owner owner);

    @Named("noAnimalAppointment")
    @Mappings({
            @Mapping(target = "animal", ignore = true),
            @Mapping(target = "dateTime", qualifiedByName = "dateTimeConversionToStringForAnimal")
    })
    AppointmentDTO convertToAppointmentDTOWithoutAnimal(Appointment appointment);

    @Named("dateTimeConversionToStringForAnimal")
    default String convertToString(LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(localDateTime.toLocalDate()));
        LocalTime localTime = localDateTime.toLocalTime();
        stringBuilder.append(" ").append(localTime.getHour());
        int minutes = localTime.getMinute();
        stringBuilder.append(":").append(minutes == 0 ? "00" : minutes);
        return stringBuilder.toString();
    }
}
