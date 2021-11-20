package com.mitskevich.course_7sem.repository;

import com.mitskevich.course_7sem.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
    Owner findByUserId(UUID userId);
    Owner findByEmail(String email);
}
