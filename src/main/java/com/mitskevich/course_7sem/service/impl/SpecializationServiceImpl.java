package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.DeleteEntityException;
import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.SpecializationAlreadyExistsException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Doctor;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.repository.SpecializationRepository;
import com.mitskevich.course_7sem.service.interfaces.MedicalService;
import com.mitskevich.course_7sem.service.interfaces.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final MessageSource messageSource;
    private final MedicalService medicalService;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepository specializationRepository, MessageSource messageSource, MedicalService medicalService) {
        this.specializationRepository = specializationRepository;
        this.messageSource = messageSource;
        this.medicalService = medicalService;
    }

    @Override
    public Specialization saveSpecialization(Specialization specialization) {
        if (specializationRepository.findByName(specialization.getName()).isEmpty()) {
            for (MedicalServiceEntity medicalServiceEntity: specialization.getMedicalServiceEntities()) {
                medicalServiceEntity.setSpecialization(specialization);
            }
            return specializationRepository.save(specialization);
        } else {
            throw new SpecializationAlreadyExistsException(ErrorInfo.SPECIALIZATION_ALREADY_EXISTS_EXCEPTION,
                    messageSource.getMessage("message.SpecializationAlreadyExistsException", null, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public Specialization saveDraftSpecialization(UUID specializationId) {
        Specialization specialization = null;
        if (specializationId != null) {
            specialization = findById(specializationId);
        }
        if (specialization == null) {
            specialization = new Specialization();
            return specializationRepository.save(specialization);
        }
        else {
            return specialization;
        }
    }

    @Override
    public Specialization updateSpecialization(UUID uuid, Specialization specialization) {
        return specializationRepository.findById(uuid).map(specialization1 -> {
            specialization1.setName(specialization.getName());
            specialization1.setDescription(specialization.getDescription());
            if (specialization1.getDoctors() != null && specialization.getDoctors() != null) {
                List<Doctor> doctors = specialization1.getDoctors();
                doctors.clear();
                doctors.addAll(specialization.getDoctors());
            }
            if (specialization1.getMedicalServiceEntities() != null && specialization.getMedicalServiceEntities() != null) {
                List<MedicalServiceEntity> oldMedicalServiceEntities = specialization1.getMedicalServiceEntities();
                oldMedicalServiceEntities.clear();
                specialization.getMedicalServiceEntities().forEach(medicalServiceEntity -> medicalServiceEntity.setSpecialization(specialization));
                oldMedicalServiceEntities.addAll(specialization.getMedicalServiceEntities());
            }
            return specializationRepository.save(specialization1);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{uuid,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.Specialization", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteById(UUID uuid) {
        try {
            specializationRepository.deleteById(uuid);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{uuid, messageSource.getMessage("entity.Specialization", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_EXCEPTION,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public Specialization findById(UUID uuid) {
        return specializationRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{uuid,
                                        messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                        messageSource.getMessage("entity.Specialization", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }
}
