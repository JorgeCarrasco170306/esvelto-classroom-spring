package com.esvelto.classroom.student.service;

import com.esvelto.classroom.common.utils.models.GenericCrudService;
import com.esvelto.classroom.student.DTOS.StudentRequestDTO;
import com.esvelto.classroom.student.DTOS.StudentResponseDTO;

import java.util.UUID;

public interface StudentService extends GenericCrudService<StudentResponseDTO, StudentRequestDTO, UUID> {
}
