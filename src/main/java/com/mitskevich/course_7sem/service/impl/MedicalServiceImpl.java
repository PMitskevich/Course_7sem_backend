package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.DeleteEntityException;
import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.MedicalServiceEntity;
import com.mitskevich.course_7sem.model.Specialization;
import com.mitskevich.course_7sem.repository.MedicalServiceRepository;
import com.mitskevich.course_7sem.service.interfaces.MedicalService;
import com.mitskevich.course_7sem.service.interfaces.SpecializationService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicalServiceImpl implements MedicalService {

    private final MedicalServiceRepository medicalServiceRepository;
//    private final SpecializationService specializationService;
    private final MessageSource messageSource;

    public MedicalServiceImpl(MedicalServiceRepository medicalServiceRepository,
//                              SpecializationService specializationService,
                              MessageSource messageSource) {
        this.medicalServiceRepository = medicalServiceRepository;
//        this.specializationService = specializationService;
        this.messageSource = messageSource;
    }

    @Override
    public MedicalServiceEntity findById(UUID id) {
        return medicalServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{id,
                                        messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                        messageSource.getMessage("entity.MedicalService", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<MedicalServiceEntity> findAll() {
        return medicalServiceRepository.findAll();
    }

    @Override
    public List<MedicalServiceEntity> findBySpecializationId(UUID specializationId) {
        return medicalServiceRepository.findBySpecializationId(specializationId);
    }

    @Override
    public MedicalServiceEntity updateMedicalService(MedicalServiceEntity medicalServiceEntity, UUID uuid) {
        return medicalServiceRepository.findById(uuid).map(foundService -> {
            foundService.setName(medicalServiceEntity.getName());
            foundService.setPrice(medicalServiceEntity.getPrice());
            foundService.setSpecialization(medicalServiceEntity.getSpecialization());
            return medicalServiceRepository.save(foundService);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{uuid,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.MedicalService", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteMedicalService(UUID uuid) {
        try {
            medicalServiceRepository.deleteById(uuid);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{uuid, messageSource.getMessage("entity.MedicalService", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_EXCEPTION,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

//    @Override
//    public void updateMedicalServiceEntities(List<MedicalServiceEntity> serviceEntities, UUID specializationId) {
//        Specialization specialization = specializationService.findById(specializationId);
//        if (serviceEntities != null) {
//            for (MedicalServiceEntity medicalServiceEntity: serviceEntities) {
//                if (medicalServiceEntity.getId() != null) {
//                    medicalServiceEntity.setSpecialization(specialization);
//                    updateMedicalService(medicalServiceEntity, medicalServiceEntity.getId());
//                }
//            }
//        }
//    }
}
