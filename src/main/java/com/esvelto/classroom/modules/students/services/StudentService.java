package com.esvelto.classroom.modules.students.services;

import java.util.UUID;

import com.esvelto.classroom.modules.base.services.GenericService;
import com.esvelto.classroom.modules.students.dtos.StudentRequest;
import com.esvelto.classroom.modules.students.dtos.StudentResponse;

public interface StudentService extends GenericService<StudentResponse, StudentRequest, UUID> {
}
