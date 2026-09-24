package com.esvelto.classroom.student.models;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.models.BaseClass;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity()
@Table(name = "students")
public class Student extends BaseClass {
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
}
