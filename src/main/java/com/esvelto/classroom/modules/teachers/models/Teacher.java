package com.esvelto.classroom.modules.teachers.models;

import java.util.List;

import com.esvelto.classroom.modules.auth.models.User;
import com.esvelto.classroom.modules.base.models.BaseClass;
import com.esvelto.classroom.modules.institutions.models.Institution;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher extends BaseClass {

    @OneToOne()
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Institution> institutions;

}
