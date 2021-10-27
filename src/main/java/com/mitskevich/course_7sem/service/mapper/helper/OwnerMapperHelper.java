package com.mitskevich.course_7sem.service.mapper.helper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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

    @Mapping(target = "animals", qualifiedByName = "noOwnerAnimals")
    List<AnimalDTO> getAnimalDTOList(Collection<Animal> animals);
    List<Animal> getAnimalList(Collection<AnimalDTO> animals);

    @Mapping(target = "appointments", qualifiedByName = "noOwnerAppointments")
    List<AppointmentDTO> getAppointmentDTOList(Collection<Appointment> appointments);
    List<Appointment> getAppointmentList(Collection<AppointmentDTO> appointments);

    Appointment convertToAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO convertToAppointmentDTO(Appointment appointment);
    DoctorDTO convertToDoctorDTO(Doctor doctor);
    Doctor convertToDoctor(DoctorDTO doctor);
}
