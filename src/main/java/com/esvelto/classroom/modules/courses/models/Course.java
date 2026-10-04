package com.esvelto.classroom.modules.courses.models;

import com.esvelto.classroom.modules.base.models.BaseClass;
import com.esvelto.classroom.modules.institutions.models.Institution;
import com.esvelto.classroom.modules.teachers.models.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "courses")
@Entity
@Getter
@Setter
public class Course extends BaseClass {

    private String name;

    @ManyToOne()
    @JoinColumn(name = "institutions_id", nullable = false)
    private Institution institution;

}
