package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.repository.AnimalRepository;
import com.mitskevich.course_7sem.service.interfaces.AnimalService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final MessageSource messageSource;

    public AnimalServiceImpl(AnimalRepository animalRepository, MessageSource messageSource) {
        this.animalRepository = animalRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Animal findById(UUID animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{animalId, messageSource.getMessage("entity.Animal", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        return null;
    }

    @Override
    public void deleteAnimal(UUID animalId) {

    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> findAnimalsByOwnerId(UUID ownerId) {
        return animalRepository.findByOwnerId(ownerId);
    }
}
