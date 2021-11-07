package com.mitskevich.course_7sem.dto;

import com.mitskevich.course_7sem.model.Owner;
import com.mitskevich.course_7sem.model.enums.Role;
import com.mitskevich.course_7sem.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private String password;
    private Role role;
    private Status status;
    private OwnerDTO owner;
    private List<ReviewDTO> reviews;
}
