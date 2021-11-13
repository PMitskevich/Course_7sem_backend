package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.dto.AuthenticationRequestDTO;
import com.mitskevich.course_7sem.exception.InvalidEmailOrPasswordException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import com.mitskevich.course_7sem.service.interfaces.AuthenticationService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    public AuthenticationController(AuthenticationService authenticationService, MessageSource messageSource) {
        this.authenticationService = authenticationService;
        this.messageSource = messageSource;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO authenticationDTO) {
        try {
            return authenticationService.authenticate(authenticationDTO);
        } catch (AuthenticationException exception) {
            throw new InvalidEmailOrPasswordException(ErrorInfo.INVALID_EMAIL_OR_PASSWORD_EXCEPTION,
                    messageSource.getMessage("message.InvalidEmailOrPasswordException", new Object[]{}, LocaleContextHolder.getLocale()));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return ResponseEntity.ok(Collections.singletonMap("response",
                        messageSource.getMessage("message.UserSuccessfullyLoggedOut", new Object[]{}, LocaleContextHolder.getLocale())));
    }
}
