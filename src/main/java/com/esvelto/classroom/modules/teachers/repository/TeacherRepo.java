package com.esvelto.classroom.modules.teachers.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esvelto.classroom.modules.teachers.models.Teacher;

@Repository 
public interface TeacherRepo extends JpaRepository<Teacher, UUID> {
    boolean existsByUserId(UUID userId);
    Optional<Teacher> findByUserId(UUID userId);
}
