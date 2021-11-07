package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
