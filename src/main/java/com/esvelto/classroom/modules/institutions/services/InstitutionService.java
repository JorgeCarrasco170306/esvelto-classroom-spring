package com.esvelto.classroom.modules.institutions.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.esvelto.classroom.modules.base.services.GenericService;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionRequest;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;

@Service
public interface InstitutionService extends GenericService<InstitutionResponse, InstitutionRequest, UUID> {
    Page<InstitutionResponse> findAll(Pageable pageable, String name);

    void addStudentToInstitution(UUID institutionId, UUID userId);
}
