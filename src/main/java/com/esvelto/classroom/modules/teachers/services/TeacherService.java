package com.esvelto.classroom.modules.teachers.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.esvelto.classroom.modules.base.services.GenericService;
import com.esvelto.classroom.modules.teachers.dtos.TeacherRequest;
import com.esvelto.classroom.modules.teachers.dtos.TeacherResponse;

@Service 
public interface TeacherService extends GenericService<TeacherResponse, TeacherRequest, UUID>{
    
}
