package com.esvelto.classroom.student.repository;

import com.esvelto.classroom.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    boolean existsByUser_Id(UUID userId);
}
