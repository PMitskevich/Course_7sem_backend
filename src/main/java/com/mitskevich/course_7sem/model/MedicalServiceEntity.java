package com.mitskevich.course_7sem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
import java.util.UUID;

@Entity
@Table(name = "service", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class MedicalServiceEntity {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "service_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    public MedicalServiceEntity(UUID id, String name, String price, Specialization specialization) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.specialization = specialization;
    }
}
