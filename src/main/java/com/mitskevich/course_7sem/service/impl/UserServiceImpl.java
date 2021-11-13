package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.exception.DeleteEntityException;
import com.mitskevich.course_7sem.exception.ResourceNotFoundException;
import com.mitskevich.course_7sem.exception.UserAlreadyExistsException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.model.Review;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.repository.UserRepository;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MessageSource messageSource;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           MessageSource messageSource) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.messageSource = messageSource;
    }

//    @Override
//    public User preSaveOperation(User userRequest) {
//        User user = new User();
//
//        String password = userRequest.getPassword();
//        user.setEmail(userRequest.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(password));
//        user.setRole(Role.USER);
//        user.setStatus(Status.ACTIVE);
//        user.setOwner(userRequest.getOwner());
//        owner.setFirstName(temporaryUserAndOwner.getFirstName());
//        owner.setLastName(temporaryUserAndOwner.getLastName());
//        owner.setPatronymic(temporaryUserAndOwner.getPatronymic());
//        owner.setAddress(temporaryUserAndOwner.getAddress());
//        owner.setEmail(temporaryUserAndOwner.getEmail());
//        owner.setPhone(temporaryUserAndOwner.getPhone());
//
//        return user;
//    }

    @Override
    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new UserAlreadyExistsException(ErrorInfo.USER_ALREADY_EXISTS_EXCEPTION,
                messageSource.getMessage("message.UserAlreadyExistsException", null, LocaleContextHolder.getLocale()));
    }

    @Override
    public User saveAdmin(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new UserAlreadyExistsException(ErrorInfo.USER_ALREADY_EXISTS_EXCEPTION,
                messageSource.getMessage("message.UserAlreadyExistsException", null, LocaleContextHolder.getLocale()));
    }

    @Override
    public User update(UUID id, User user) {
        return userRepository.findById(id).map(user1 -> {
            user1.setEmail(user.getEmail());
            user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user1.setOwner(user.getOwner());
            user1.setRole(user.getRole());
            user1.setStatus(user.getStatus());
            if (user.getReviews() != null) {
                List<Review> reviews = user1.getReviews();
                reviews.clear();
                reviews.addAll(user.getReviews());
            }
            return userRepository.save(user1);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{id,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public void delete(UUID userId) {
        try {
            userRepository.deleteById(userId);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{userId, messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_EXCEPTION,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND, messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{userId,
                                messageSource.getMessage("criterion.id", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND, messageSource.getMessage("message.ResourceNotFound",
                        new Object[]{email,
                                messageSource.getMessage("criterion.email", null, LocaleContextHolder.getLocale()),
                                messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
