package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.service.mapper.helper.OwnerMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {OwnerMapperHelper.class})
public interface OwnerMapper {
    Owner convertToOwner(OwnerDTO ownerDTO);
    @Mapping(target = "user", qualifiedByName = "noOwnerUser")
    OwnerDTO convertToOwnerDTO(Owner owner);
}
