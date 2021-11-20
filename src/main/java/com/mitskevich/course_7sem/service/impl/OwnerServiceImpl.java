package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.DeleteEntityException;
import com.mitskevich.course_7sem.exception.OwnerAlreadyExistsException;
import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Animal;
import com.mitskevich.course_7sem.model.Appointment;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.model.enums.Role;
import com.mitskevich.course_7sem.model.enums.Status;
import com.mitskevich.course_7sem.repository.OwnerRepository;
import com.mitskevich.course_7sem.service.interfaces.OwnerService;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final UserService userService;
    private final MessageSource messageSource;

    public OwnerServiceImpl(OwnerRepository ownerRepository, UserService userService, MessageSource messageSource) {
        this.ownerRepository = ownerRepository;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @Override
    public Owner findById(UUID ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                        messageSource.getMessage("message.ResourceNotFound",
                                new Object[]{ownerId, messageSource.getMessage("entity.Owner", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public Owner saveOwner(Owner owner) {
        Owner existingOwner = ownerRepository.findByEmail(owner.getEmail());
        if (existingOwner != null && existingOwner.getPhone().equals(owner.getPhone())) {
            throw new OwnerAlreadyExistsException(ErrorInfo.OWNER_ALREADY_EXISTS_EXCEPTION,
                    messageSource.getMessage("message.OwnerAlreadyExistsException", new Object[]{}, LocaleContextHolder.getLocale()));
        }
        User persistedUser = userService.save(userService.preSaveOperation(owner));
        owner.setUser(persistedUser);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner, UUID ownerId) {
        return ownerRepository.findById(ownerId).map(owner1 -> {
            owner1.setFirstName(owner.getFirstName());
            owner1.setLastName(owner.getLastName());
            owner1.setPatronymic(owner.getPatronymic());
            owner1.setAddress(owner.getAddress());
            owner1.setEmail(owner.getEmail());
            owner1.setPhone(owner.getPhone());
            User existingUser = owner.getUser();
            owner1.setUser(userService.update(existingUser.getId(), existingUser));
            if (owner.getAnimals() != null) {
                List<Animal> animals = owner.getAnimals();
                animals.clear();
                animals.addAll(owner1.getAnimals());
            }
            if (owner.getAppointments() != null) {
                List<Appointment> appointments = owner.getAppointments();
                appointments.clear();
                appointments.addAll(owner1.getAppointments());
            }
            return ownerRepository.save(owner1);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{ownerId,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.Owner", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteOwner(UUID ownerId) {
        try {
            userService.delete(findById(ownerId).getUser().getId());
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{ownerId, messageSource.getMessage("entity.Owner", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_EXCEPTION,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findByUserId(UUID userId) {
        return ownerRepository.findByUserId(userId);
    }
}
