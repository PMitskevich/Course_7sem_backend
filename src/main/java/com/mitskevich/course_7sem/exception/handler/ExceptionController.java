package com.mitskevich.course_7sem.exception.handler;

import com.mitskevich.course_7sem.exception.BaseException;
import com.mitskevich.course_7sem.exception.JwtAuthenticationException;
import com.mitskevich.course_7sem.exception.detail.ExceptionDetails;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    private final MessageSource messageSource;

    public ExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException exception) {
        String errorTitle = exception.getErrorInfo().getErrorTitle();
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setErrorTitle(messageSource.getMessage(errorTitle, null, LocaleContextHolder.getLocale()));
        exceptionDetails.setTimestamp(new Date());
        exceptionDetails.setErrorCode(exception.getErrorInfo().getErrorCode());
        exceptionDetails.setMessage(exception.getMessage());
        HttpStatus httpStatus = exception.getErrorInfo().getHttpStatus();
        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleruntimeException(RuntimeException exception) {
//        String errorTitle = exception.getErrorInfo().getErrorTitle();
//        ExceptionDetails exceptionDetails = new ExceptionDetails();
//        exceptionDetails.setErrorTitle(messageSource.getMessage(errorTitle, null, LocaleContextHolder.getLocale()));
//        exceptionDetails.setTimestamp(new Date());
//        exceptionDetails.setErrorCode(exception.getErrorInfo().getErrorCode());
//        exceptionDetails.setMessage(exception.getMessage());
//        HttpStatus httpStatus = exception.getErrorInfo().getHttpStatus();
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
