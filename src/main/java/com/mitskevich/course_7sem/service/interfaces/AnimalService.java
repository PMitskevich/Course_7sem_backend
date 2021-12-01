package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Animal;

import java.util.List;
import java.util.UUID;

public interface AnimalService {
    Animal findById(UUID animalId);
    Animal saveAnimal(Animal animal);
    Animal updateAnimal(Animal animal);
    void deleteAnimal(UUID animalId);
    List<Animal> findAll();
    List<Animal> findAnimalsByOwnerId(UUID ownerId);
}
