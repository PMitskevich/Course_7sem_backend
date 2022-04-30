package com.mitskevich.course_7sem.model.statistics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineGraphNoteClass {
    private LocalDate date;
    private String specName;
    private Long count;
}
