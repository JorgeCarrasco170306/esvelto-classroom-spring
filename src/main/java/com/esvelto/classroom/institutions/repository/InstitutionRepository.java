package com.esvelto.classroom.institutions.repository;

import com.esvelto.classroom.institutions.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface InstitutionRepository extends JpaRepository<Institution, UUID> {
}
