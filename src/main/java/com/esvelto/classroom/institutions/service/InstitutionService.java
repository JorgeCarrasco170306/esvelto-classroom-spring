package com.esvelto.classroom.institutions.service;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.common.utils.models.GenericCrudService;
import com.esvelto.classroom.institutions.DTO.InstitutionRequestDTO;
import com.esvelto.classroom.institutions.DTO.InstitutionResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface InstitutionService extends GenericCrudService<InstitutionResponseDTO, InstitutionRequestDTO, UUID> {

    InstitutionResponseDTO create(User user, InstitutionRequestDTO dto, MultipartFile file);

}
