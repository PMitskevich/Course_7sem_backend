package com.mitskevich.course_7sem.service.interfaces;

import com.mitskevich.course_7sem.model.statistics.LineGraphNote;
import com.mitskevich.course_7sem.model.statistics.LineGraphNoteClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StatisticsService {
    Map<LocalDate, Map<String, Long>> getLineGraphNotes();
    List<LineGraphNoteClass> getLineGraphNotes(List<UUID> specializations);
}
