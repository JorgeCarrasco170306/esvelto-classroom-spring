package com.esvelto.classroom.curso.service;

import com.esvelto.classroom.common.utils.models.GenericCrudService;
import com.esvelto.classroom.curso.DTO.CursoRequestDTO;
import com.esvelto.classroom.curso.DTO.CursoResponseDTO;

import java.util.UUID;

public interface CursoService extends GenericCrudService<CursoResponseDTO, CursoRequestDTO, UUID> {
}
