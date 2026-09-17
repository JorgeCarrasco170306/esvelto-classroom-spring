package com.esvelto.classroom.students.repository;

import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.students.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface
public interface StudentRepository extends JpaRepository<Student, UUID> {
}
