package com.esvelto.classroom.modules.courses.services;

import com.esvelto.classroom.modules.base.services.GenericService;
import com.esvelto.classroom.modules.courses.dto.CourseRequest;
import com.esvelto.classroom.modules.courses.dto.CourseResponse;

import java.util.UUID;

public interface CourseService extends GenericService<CourseResponse, CourseRequest, UUID> {
}
