package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class OwnerAlreadyExistsException extends BaseException {
    public OwnerAlreadyExistsException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
