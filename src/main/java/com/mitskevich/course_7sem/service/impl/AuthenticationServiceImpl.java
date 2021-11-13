package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.dto.AuthenticationRequestDTO;
import com.mitskevich.course_7sem.model.User;
import com.mitskevich.course_7sem.security.JwtTokenProvider;
import com.mitskevich.course_7sem.service.interfaces.AuthenticationService;
import com.mitskevich.course_7sem.service.interfaces.UserService;
import com.mitskevich.course_7sem.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MessageSource messageSource;
    private final UserMapper userMapper;
    @Value("${jwt.header}")
    private String authorizationHeader;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserService userService, JwtTokenProvider jwtTokenProvider, MessageSource messageSource, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.messageSource = messageSource;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticationRequestDTO authenticationDTO) {
        String email = authenticationDTO.getEmail();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                authenticationDTO.getPassword()));
        User user = userService.findByEmail(email);
        String token = jwtTokenProvider.createToken(email, user.getRole().name());
        HttpHeaders headers = new HttpHeaders();
        headers.add(authorizationHeader, token);
        return ResponseEntity.ok().headers(headers).body(userMapper.convertToUserDTO(user));
//        return ResponseEntity.ok().headers(headers).body(
//                Collections.singletonMap("response",
//                        messageSource.getMessage("message.UserSuccessfullyAuthenticated", new Object[]{}, LocaleContextHolder.getLocale())));
    }
}
