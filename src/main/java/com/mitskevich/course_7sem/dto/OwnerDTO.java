package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OwnerDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;
    private String phone;
    private UserDTO user;
    private List<AnimalDTO> animals;
    private List<AppointmentDTO> appointments;
}
