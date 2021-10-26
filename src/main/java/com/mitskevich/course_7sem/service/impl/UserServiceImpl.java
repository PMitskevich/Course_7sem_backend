package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.model.TemporaryUserAndOwner;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.repository.UserRepository;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User preSaveOperation(TemporaryUserAndOwner temporaryUserAndOwner) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(UUID uuid, User user) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public User findById(UUID uuid) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
