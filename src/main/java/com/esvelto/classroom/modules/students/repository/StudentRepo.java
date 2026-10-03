package com.esvelto.classroom.modules.students.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esvelto.classroom.modules.students.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID> {

    boolean existsByUserId(UUID userId);
}
