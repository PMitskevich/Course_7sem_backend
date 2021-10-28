package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OwnerDTO {
    private BigInteger id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;
    private String phone;
    private UserDTO user;
    private List<AnimalDTO> animals;
    private List<AppointmentDTO> appointments;
}
