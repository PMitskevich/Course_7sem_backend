package com.mitskevich.course_7sem.model.statistics;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface LineGraphNote {
    LocalDate getDate();
    String getName();
    Integer getCount();
}
