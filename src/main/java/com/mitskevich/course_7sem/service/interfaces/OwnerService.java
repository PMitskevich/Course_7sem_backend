package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.Owner;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    Owner findById(UUID ownerId);
    Owner saveOwner(Owner owner);
    Owner updateOwner(Owner owner, UUID ownerId);
    void deleteOwner(UUID owner);
    List<Owner> findAll();
    Owner findByUserId(UUID userId);
}
