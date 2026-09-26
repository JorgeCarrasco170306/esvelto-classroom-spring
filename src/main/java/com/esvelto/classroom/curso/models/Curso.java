package com.esvelto.classroom.curso.models;

import com.esvelto.classroom.common.utils.models.Auditable;
import com.esvelto.classroom.institutions.models.Institution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Curso extends Auditable {

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "courses_institutions",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "institution_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "institution_id"})
    )
    private Set<Institution> institutions = new HashSet<>();

    public void addInstitution(Institution institution) {
        institutions.add(institution);
    }
}
