package com.esvelto.classroom.modules.homework.models;

import com.esvelto.classroom.modules.base.models.BaseClass;
import com.esvelto.classroom.modules.courses.models.Course;
import com.esvelto.classroom.modules.students.models.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "homeworks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Homework extends BaseClass {

    private String name;
    private String description;
    private LocalDateTime assignmentDate;
    private LocalDateTime expirationTime;

    @ManyToMany(mappedBy = "homeworks")
    private List<Student> students;

    @ManyToOne()
    private Course course;
}
