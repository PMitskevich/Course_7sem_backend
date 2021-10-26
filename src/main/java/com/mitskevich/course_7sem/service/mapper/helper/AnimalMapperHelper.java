package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AnimalMapperHelper {
    @Named("noOwnerAnimals")
    @Mapping(target = "owner", ignore = true)
    AnimalDTO convertToAnimalDTOWithoutOwner(Animal animal);

    @Named("noAppointmentAnimal")
    @Mapping(target = "appointments", ignore = true)
    AnimalDTO convertToAnimalDTOWithoutAppointments(Animal animal);

    @Mapping(target = "appointments", qualifiedByName = "noAnimalAppointment")
    List<AppointmentDTO> getAppointmentDTOList(Collection<Appointment> appointments);

    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);
}
