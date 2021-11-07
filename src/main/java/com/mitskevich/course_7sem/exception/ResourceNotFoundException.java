package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
