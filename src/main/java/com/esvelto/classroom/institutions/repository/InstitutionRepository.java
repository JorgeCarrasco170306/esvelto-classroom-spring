package com.esvelto.classroom.institutions.repository;

import com.esvelto.classroom.institutions.models.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface
public interface InstitutionRepository extends JpaRepository<Institution, UUID> {

    boolean existsByName(String name);

}
