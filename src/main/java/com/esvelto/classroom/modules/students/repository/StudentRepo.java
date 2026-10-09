package com.esvelto.classroom.modules.students.repository;

import java.util.Optional;
import java.util.UUID;

import com.esvelto.classroom.modules.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esvelto.classroom.modules.students.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID> {
    Optional<Student> findByUserId(UUID userId);
    boolean existsByUserId(UUID userId);
}
