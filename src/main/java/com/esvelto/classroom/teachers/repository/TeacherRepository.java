package com.esvelto.classroom.teachers.repository;

import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.teachers.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}
