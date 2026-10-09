package com.esvelto.classroom.modules.homework.repository;

import com.esvelto.classroom.modules.homework.models.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, UUID> {
}
