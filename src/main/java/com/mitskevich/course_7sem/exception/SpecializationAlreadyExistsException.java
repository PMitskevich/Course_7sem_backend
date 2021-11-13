package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class SpecializationAlreadyExistsException extends BaseException {
    public SpecializationAlreadyExistsException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
