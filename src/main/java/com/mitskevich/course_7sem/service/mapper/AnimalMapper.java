package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.service.mapper.helper.AnimalMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AnimalMapperHelper.class})
public interface AnimalMapper {
    Animal convertToAnimal(AnimalDTO animal);
    @Mapping(target = "owner", qualifiedByName = "noOwnerAnimals")
    AnimalDTO convertToAnimalDTO(Animal animal);
}
