package com.mitskevich.course_7sem.service.impl;

import com.mitskevich.course_7sem.model.statistics.LineGraphNoteClass;
import com.mitskevich.course_7sem.service.interfaces.StatisticsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final EntityManager entityManager;

    public StatisticsServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Map<LocalDate, Map<String, Long>> getLineGraphNotes() {
        Query query = entityManager.createQuery("SELECT function('date_trunc', 'day', app.dateTime) as date, spec.name, count(spec.id) as count " +
                "FROM Doctor d " +
                "INNER JOIN d.appointments app " +
                "INNER JOIN d.specializations spec " +
                "GROUP BY spec.id, function('date_trunc', 'day', app.dateTime)");
        List<Object[]> notes = query.getResultList();
        List<LineGraphNoteClass> lineElements = notes.stream().map(note -> {
            LineGraphNoteClass element = new LineGraphNoteClass();
            element.setDate(((Timestamp) note[0]).toLocalDateTime().toLocalDate());
            element.setSpecName(String.valueOf(note[1]));
            element.setCount((Long) note[2]);
            return element;
        }).sorted(Comparator.comparing(LineGraphNoteClass::getDate)).collect(Collectors.toList());
        return handleLineGraphNotesToDisplay(lineElements);
    }

    @Override
    public List<LineGraphNoteClass> getLineGraphNotes(List<UUID> specializations) {
        return null;
    }

    private Map<LocalDate, Map<String, Long>> handleLineGraphNotesToDisplay(List<LineGraphNoteClass> notes) {
        Map<LocalDate, Map<String, Long>> preparedMap = new HashMap<>();
        for (LineGraphNoteClass note: notes) {
            Map<String, Long> specCount = preparedMap.get(note.getDate());
            if (specCount == null) {
                specCount = new HashMap<>();
            }
            specCount.put(note.getSpecName(), note.getCount());
            preparedMap.put(note.getDate(), specCount);
        }
        return preparedMap;
    }
}
