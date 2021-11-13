package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.dto.AuthenticationRequestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> authenticate(AuthenticationRequestDTO authenticationDTO);
}
