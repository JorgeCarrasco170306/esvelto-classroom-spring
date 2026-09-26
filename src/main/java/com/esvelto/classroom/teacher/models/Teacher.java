package com.esvelto.classroom.teacher.models;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.models.BaseClass;
import com.esvelto.classroom.institutions.models.Institution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher extends BaseClass {

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "teacher")
    private Set<Institution> institutions;
}
