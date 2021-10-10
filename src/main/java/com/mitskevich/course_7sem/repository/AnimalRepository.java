package com.mitskevich.course_7sem.repository;

import com.mitskevich.course_7sem.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    List<Animal> findByOwnerId(UUID ownerId);
}
