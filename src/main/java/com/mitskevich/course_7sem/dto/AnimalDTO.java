package com.mitskevich.course_7sem.dto;

import com.mitskevich.course_7sem.model.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class AnimalDTO {
    private UUID id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private Gender gender;
    private OwnerDTO owner;
    private List<AppointmentDTO> appointments;
}
