package com.esvelto.classroom.student.models;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.models.BaseClass;
import com.esvelto.classroom.institutions.models.Institution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends BaseClass {
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToMany(mappedBy = "students")
    private Set<Institution> institution;
}
