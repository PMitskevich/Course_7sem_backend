package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class InvalidEmailOrPasswordException extends BaseException {
    public InvalidEmailOrPasswordException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
