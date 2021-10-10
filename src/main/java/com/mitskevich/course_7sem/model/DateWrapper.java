package com.mitskevich.course_7sem.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class DateWrapper {
    private UUID scheduleDayId;
    private LocalDate date;
    private String shortDateName;
    private boolean isBlocked;

    public DateWrapper(UUID scheduleDayId, LocalDate date, String shortDateName) {
        this.scheduleDayId = scheduleDayId;
        this.date = date;
        this.shortDateName = shortDateName;
    }
}
