package com.esvelto.classroom.institutions.models;

import com.esvelto.classroom.common.utils.models.Auditable;
import com.esvelto.classroom.curso.models.Curso;
import com.esvelto.classroom.student.models.Student;
import com.esvelto.classroom.teacher.models.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "institutions")
@Getter
@Setter
public class Institution extends Auditable {

    private String name;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "institutions_students",
            joinColumns = @JoinColumn(name = "institution_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    @ManyToMany(mappedBy = "institutions")
    private Set<Curso> cursos;

}
