package com.mitskevich.course_7sem.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "schedule_day", schema = "public")
@Getter
@Setter
public class ScheduleDay {
    @Id
    @GeneratedValue
    @Column(name = "schedule_day_id")
    private BigInteger id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "scheduleDay", orphanRemoval = true)
    private List<ScheduleTime> scheduleTimes;

    public ScheduleDay() {
        this.isBlocked = false;
    }

    public ScheduleDay(LocalDate date) {
        this.date = date;
        this.isBlocked = false;
    }
}
