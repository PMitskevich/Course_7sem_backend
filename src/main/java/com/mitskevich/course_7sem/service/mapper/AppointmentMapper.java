package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.service.mapper.helper.AnimalMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.AppointmentMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.DoctorMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.OwnerMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {AppointmentMapperHelper.class, DoctorMapperHelper.class, OwnerMapperHelper.class, AnimalMapperHelper.class})
public interface AppointmentMapper {
    Appointment convertToAppointment(AppointmentDTO appointmentDTO);
    @Mappings({
            @Mapping(target = "doctor", qualifiedByName = "noAppointmentDoctor"),
            @Mapping(target = "owner", qualifiedByName = "noAppointmentOwner"),
            @Mapping(target = "animal", qualifiedByName = "noAppointmentAnimal")
    })
    AppointmentDTO convertToAppointmentDTO(Appointment appointment);
}
