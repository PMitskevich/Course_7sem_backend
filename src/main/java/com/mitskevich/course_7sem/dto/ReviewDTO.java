package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class ReviewDTO {
    private UUID id;
    private String description;
    private UserDTO user;
}
