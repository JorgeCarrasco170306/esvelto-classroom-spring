package com.esvelto.classroom.modules.teachers.dtos;

import java.util.List;

import com.esvelto.classroom.modules.auth.dtos.UserResponse;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;
import com.esvelto.classroom.modules.teachers.models.Teacher;

public record TeacherResponse(
        UserResponse user,
        List<InstitutionResponse> institutions) {

    public static TeacherResponse from(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        return new TeacherResponse(
                UserResponse.from(teacher.getUser()),
                teacher.getInstitutions() != null
                        ? teacher.getInstitutions().stream().map(InstitutionResponse::from).toList()
                        : List.of()
        );
    }
}
