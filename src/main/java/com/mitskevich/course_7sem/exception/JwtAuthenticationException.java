package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class JwtAuthenticationException extends BaseException {
    public JwtAuthenticationException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
