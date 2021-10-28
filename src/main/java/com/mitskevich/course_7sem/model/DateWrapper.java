package com.mitskevich.course_7sem.model;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class DateWrapper {
    private BigInteger scheduleDayId;
    private LocalDate date;
    private String shortDateName;
    private boolean isBlocked;

    public DateWrapper(BigInteger scheduleDayId, LocalDate date, String shortDateName) {
        this.scheduleDayId = scheduleDayId;
        this.date = date;
        this.shortDateName = shortDateName;
    }
}
