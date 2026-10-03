package com.esvelto.classroom.modules.teachers.dtos;

import java.util.List;

import com.esvelto.classroom.modules.auth.dtos.UserResponse;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;

public record TeacherResponse(
        UserResponse user,
        List<InstitutionResponse> institutions) {

}
