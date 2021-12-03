package com.mitskevich.course_7sem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
