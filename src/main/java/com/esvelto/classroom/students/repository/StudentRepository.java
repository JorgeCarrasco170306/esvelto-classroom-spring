package com.esvelto.classroom.students.repository;

import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.students.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface StudentRepository extends JpaRepository<Student, UUID> {
}
