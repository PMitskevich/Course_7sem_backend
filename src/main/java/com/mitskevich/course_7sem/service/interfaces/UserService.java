package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.TemporaryUserAndOwner;
import com.mitskevich.course_7sem.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User preSaveOperation(TemporaryUserAndOwner temporaryUserAndOwner);
    User save(User user);
    User update(UUID uuid, User user);
    void delete(UUID uuid);
    User findById(UUID uuid);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
