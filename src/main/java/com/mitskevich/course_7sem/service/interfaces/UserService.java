package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.TemporaryUserAndOwner;
import com.mitskevich.course_7sem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
//    User preSaveOperation(User temporaryUserAndOwner);
    User save(User user);
    User saveAdmin(User user);
    User update(UUID id, User user);
    void delete(UUID uuid);
    User findById(UUID uuid);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
