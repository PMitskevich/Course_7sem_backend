package com.mitskevich.course_7sem.model;

import com.mitskevich.course_7sem.model.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

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
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "animal", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class Animal {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "animal_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "animal")
    private List<Appointment> appointments;
}
