package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.DoctorDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.service.mapper.helper.AppointmentMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {AppointmentMapperHelper.class})
public interface AppointmentMapper {
    Appointment convertToAppointment(AppointmentDTO appointmentDTO);
    @Mappings({
            @Mapping(target = "doctor", qualifiedByName = "noAppointmentDoctor"),
            @Mapping(target = "owner", qualifiedByName = "noAppointmentOwner"),
            @Mapping(target = "animal", qualifiedByName = "noAppointmentAnimal")
    })
    AppointmentDTO convertToAppointmentDTO(Appointment appointment);
}
