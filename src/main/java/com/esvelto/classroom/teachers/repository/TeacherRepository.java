package com.esvelto.classroom.teachers.repository;

import com.esvelto.classroom.institutions.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TeacherRepository extends JpaRepository<Institution, UUID> {
}
