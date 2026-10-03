package com.esvelto.classroom.modules.students.dtos;

import java.util.List;

import com.esvelto.classroom.modules.auth.dtos.UserResponse;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;

public record StudentResponse(
        UserResponse user,
        List<InstitutionResponse> institutions) {
}
