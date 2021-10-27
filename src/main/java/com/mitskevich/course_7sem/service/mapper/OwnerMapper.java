package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.dto.AppointmentDTO;
import com.mitskevich.course_7sem.dto.OwnerDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.service.mapper.helper.OwnerMapperHelper;
import com.mitskevich.course_7sem.service.mapper.helper.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {OwnerMapperHelper.class, UserMapperHelper.class})
public interface OwnerMapper {
    @Mapping(target = "user", qualifiedByName = "noOwnerUser")
    OwnerDTO convertToOwnerDTO(Owner owner);
    Owner convertToOwner(OwnerDTO ownerDTO);
}
