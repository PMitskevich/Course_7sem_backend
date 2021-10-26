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

@Mapper
public interface AppointmentMapperHelper {
    Doctor convertToDoctor(DoctorDTO doctorDTO);
    DoctorDTO convertToDTO(Doctor doctor);
    Owner convertToOwner(OwnerDTO ownerDTO);
    OwnerDTO convertToOwnerDTO(Owner owner);
    Animal convertToAnimal(AnimalDTO animalDTO);
    AnimalDTO convertToAnimalDTO(Animal animal);

    @Named("noAnimalAppointment")
    @Mapping(target = "animal", ignore = true)
    AppointmentDTO convertToAppointmentDTOWithoutAnimal(Appointment appointment);

    @Named("noOwnerAppointments")
    @Mapping(target = "owner", ignore = true)
    AppointmentDTO convertToAppointmentDTOWithoutOwner(Appointment appointment);

    @Named("noDoctorAppointment")
    @Mapping(target = "doctor", ignore = true)
    AppointmentDTO convertToAppointmentDTOWithoutDoctor(Appointment appointment);
}
