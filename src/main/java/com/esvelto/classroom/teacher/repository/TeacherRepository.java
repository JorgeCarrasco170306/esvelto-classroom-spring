package com.esvelto.classroom.teacher.repository;

import com.esvelto.classroom.teacher.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
	boolean existsByUser_Id(UUID userId);
}