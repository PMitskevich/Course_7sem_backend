package com.mitskevich.course_7sem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ScheduleDayDTO {
    private BigInteger id;
    private LocalDate date;
    private Boolean isBlocked;
    private DoctorDTO doctor;
    private List<ScheduleTimeDTO> scheduleTimes;
}
