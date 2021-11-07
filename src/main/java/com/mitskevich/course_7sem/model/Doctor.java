package com.mitskevich.course_7sem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "doctor", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue
    @Column(name = "doctor_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @NotNull
    @Size(min = 2, max = 16)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 16)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "patronymic")
    private String patronymic;

    @NotNull
    @Size(min = 2)
    @Column(name = "address")
    private String address;

    @NotNull
    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Size(min = 4)
    @Column(name = "experience")
    private String experience;

    @ManyToMany(mappedBy = "doctors")
    private List<Specialization> specializations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctor")
    private List<ScheduleDay> scheduleDays;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doctor")
    private List<Appointment> appointments;
}
