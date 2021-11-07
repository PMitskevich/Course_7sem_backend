package com.mitskevich.course_7sem.exception.detail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionDetails {
    private String errorTitle;
    private Date timestamp;
    private String errorCode;
    private String message;
}
