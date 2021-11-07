package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ScheduleTimeDTO {
    private UUID id;
    private LocalTime time;
    private Boolean isBlocked;
    private ScheduleDayDTO scheduleDay;
}
