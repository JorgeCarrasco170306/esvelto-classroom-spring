package com.esvelto.classroom.institutions.models;

import com.esvelto.classroom.common.utils.BaseClass;
import com.esvelto.classroom.students.models.Student;
import com.esvelto.classroom.teachers.models.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.modulith.NamedInterface;

import java.util.List;

@NamedInterface
@Entity
@Table(name = "institutions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institution extends BaseClass {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String logoUrl;

    @ManyToOne()
    private Teacher teacher;

    @OneToMany(mappedBy = "institution")
    private List<Student> students;

}
