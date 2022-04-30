package com.mitskevich.course_7sem.controller;

import com.mitskevich.course_7sem.model.statistics.LineGraphNote;
import com.mitskevich.course_7sem.model.statistics.LineGraphNoteClass;
import com.mitskevich.course_7sem.service.interfaces.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/lineGraphNotes")
    public Map<LocalDate, Map<String, Long>> getLineGraphNotes() {
        return statisticsService.getLineGraphNotes();
    }

    @PostMapping("/lineGraphNotes")
    public List<LineGraphNoteClass> getLineGraphNotes(@RequestBody List<UUID> specializations) {
        return statisticsService.getLineGraphNotes(specializations);
    }
}
