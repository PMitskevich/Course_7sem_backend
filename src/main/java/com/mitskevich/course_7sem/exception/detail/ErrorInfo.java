package com.mitskevich.course_7sem.exception.detail;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorInfo {

    AUTHENTICATION_EXCEPTION("title.AuthenticationException", "0001", HttpStatus.FORBIDDEN),
    RESOURCE_NOT_FOUND("title.ResourceNotFound", "0002", HttpStatus.NOT_FOUND),
    DELETE_ENTITY_EXCEPTION("title.DeleteEntityException", "0003", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXISTS_EXCEPTION("title.UserAlreadyExistsException", "0004", HttpStatus.BAD_REQUEST),
    SPECIALIZATION_ALREADY_EXISTS_EXCEPTION("title.SpecializationAlreadyExistsException", "0005", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("title.InternalServerError", "0006", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_EMAIL_OR_PASSWORD_EXCEPTION("title.AuthenticationException", "0007", HttpStatus.FORBIDDEN),
    OWNER_ALREADY_EXISTS_EXCEPTION("title.OwnerAlreadyExistsException", "0008", HttpStatus.BAD_REQUEST);

    private final String errorTitle;
    private final String errorCode;
    private final HttpStatus httpStatus;

    ErrorInfo(String errorTitle, String errorCode, HttpStatus httpStatus) {
        this.errorTitle = errorTitle;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
