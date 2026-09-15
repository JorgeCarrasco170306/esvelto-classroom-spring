package com.esvelto.classroom.students.models;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.BaseClass;
import com.esvelto.classroom.institutions.models.Institution;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.modulith.NamedInterface;

import java.util.List;

@NamedInterface
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseClass {

    @Column(nullable = false, unique = true)
    private String studentCode;

    @OneToOne
    private User user;

    @ManyToOne
    private Institution institution;


}
