package com.mitskevich.course_7sem.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedule_time", schema = "public")
@Getter
@Setter
public class ScheduleTime {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "schedule_time_id")
    private UUID id;

    @Column(name = "schedule_time")
    private LocalTime time;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_day_id")
    private ScheduleDay scheduleDay;

    public ScheduleTime() {
        this.isBlocked = false;
    }

    public ScheduleTime(LocalTime time) {
        this.time = time;
    }
}
