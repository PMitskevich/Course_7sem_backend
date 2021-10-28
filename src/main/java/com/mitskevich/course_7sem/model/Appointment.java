package com.mitskevich.course_7sem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointment", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue
    @Column(name = "appointment_id")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(name = "date")
    private LocalDateTime dateTime;

    public Appointment(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static Appointment of(ScheduleDay date, ScheduleTime time) {
        LocalDateTime dateTime = LocalDateTime.of(date.getDate(), time.getTime());
        return new Appointment(dateTime);
    }
}
