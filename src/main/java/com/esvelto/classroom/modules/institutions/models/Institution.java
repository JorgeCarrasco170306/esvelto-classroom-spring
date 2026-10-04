package com.esvelto.classroom.modules.institutions.models;

import java.util.List;
import java.util.ArrayList;

import com.esvelto.classroom.modules.base.models.BaseClass;
import com.esvelto.classroom.modules.courses.models.Course;
import com.esvelto.classroom.modules.students.models.Student;
import com.esvelto.classroom.modules.teachers.models.Teacher;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "institutions")
@Getter
@Setter
public class Institution extends BaseClass {

    private String name;
    private String imageUrl;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();


    @ManyToMany(mappedBy = "institutions")
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

}
