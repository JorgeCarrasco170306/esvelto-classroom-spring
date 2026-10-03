package com.esvelto.classroom.modules.students.models;

import java.util.List;
import java.util.ArrayList;

import com.esvelto.classroom.modules.auth.models.User;
import com.esvelto.classroom.modules.base.models.BaseClass;
import com.esvelto.classroom.modules.institutions.models.Institution;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseClass {

    @OneToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "student_institutions", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "institution_id"))
    private List<Institution> institutions = new ArrayList<>();

}
