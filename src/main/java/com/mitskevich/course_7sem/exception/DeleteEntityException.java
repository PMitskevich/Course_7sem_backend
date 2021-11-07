package com.mitskevich.course_7sem.exception;

import com.mitskevich.course_7sem.exception.detail.ErrorInfo;

public class DeleteEntityException extends BaseException {

    public DeleteEntityException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
