package com.esvelto.classroom.teacher.service;

import com.esvelto.classroom.common.utils.models.GenericCrudService;
import com.esvelto.classroom.teacher.DTOS.TeacherRequestDTO;
import com.esvelto.classroom.teacher.DTOS.TeacherResponseDTO;
import com.esvelto.classroom.teacher.models.Teacher;

import java.util.Optional;
import java.util.UUID;

public interface TeacherService extends GenericCrudService<TeacherResponseDTO, TeacherRequestDTO, UUID> {


}
