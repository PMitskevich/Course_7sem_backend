package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.User;
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
        return null;
    }

    @Override
    public Owner updateOwner(Owner owner, UUID ownerId) {
        owner.setId(ownerId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        owner.setUser(user);
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(UUID owner) {

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
