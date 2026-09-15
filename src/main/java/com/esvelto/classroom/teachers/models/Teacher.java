package com.esvelto.classroom.teachers.models;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.BaseClass;
import com.esvelto.classroom.institutions.models.Institution;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.modulith.NamedInterface;

import java.util.ArrayList;
import java.util.List;

@NamedInterface
@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends BaseClass {

    @Column(nullable = false, unique = true)
    private String teacherCode;

    @OneToOne()
    private User user;

    @OneToMany(mappedBy = "teacher")
    List<Institution> institutions = new ArrayList<>();

}
