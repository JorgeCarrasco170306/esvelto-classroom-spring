package com.esvelto.classroom.modules.institutions.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esvelto.classroom.modules.institutions.models.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, UUID> {
    Page<Institution> findByNameContainingIgnoreCase(Pageable pageable, String name);

    boolean existsByNameAndTeacherId(String name, UUID teacherId);
}
