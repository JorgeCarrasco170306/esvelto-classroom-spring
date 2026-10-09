package com.esvelto.classroom.modules.courses.models;

import com.esvelto.classroom.modules.base.models.BaseClass;
import com.esvelto.classroom.modules.homework.models.Homework;
import com.esvelto.classroom.modules.institutions.models.Institution;
import com.esvelto.classroom.modules.students.models.Student;
import com.esvelto.classroom.modules.teachers.models.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "courses")
@Entity
@Getter
@Setter
public class Course extends BaseClass {

    private String name;

    @ManyToOne()
    @JoinColumn(name = "institutions_id", nullable = false)
    private Institution institution;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @OneToMany(mappedBy = "course")
    private List<Homework> homeworks = new ArrayList<>();

}
