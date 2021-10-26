package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
public class AppointmentDTO {
    private UUID id;
    private DoctorDTO doctor;
    private OwnerDTO owner;
    private AnimalDTO animal;
    private LocalDateTime dateTime;
}
