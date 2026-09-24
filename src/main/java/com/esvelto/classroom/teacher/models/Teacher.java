package com.esvelto.classroom.teacher.models;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.models.BaseClass;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher extends BaseClass {

    @OneToOne()
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
}
