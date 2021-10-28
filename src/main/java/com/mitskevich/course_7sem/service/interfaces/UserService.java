package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.TemporaryUserAndOwner;
import com.mitskevich.course_7sem.model.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User preSaveOperation(TemporaryUserAndOwner temporaryUserAndOwner);
    User save(User user);
    User update(BigInteger uuid, User user);
    void delete(BigInteger uuid);
    User findById(BigInteger uuid);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
