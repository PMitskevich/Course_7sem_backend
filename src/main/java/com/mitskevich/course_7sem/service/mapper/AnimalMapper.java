package com.mitskevich.course_7sem.service.mapper;

import com.mitskevich.course_7sem.dto.AnimalDTO;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.service.mapper.helper.AnimalMapperHelper;
import org.mapstruct.Mapper;

@Mapper(uses = {AnimalMapperHelper.class})
public interface AnimalMapper {
    AnimalDTO convertToAnimalDTO(Animal animal);
    Animal convertToAnimal(AnimalDTO animal);
}
