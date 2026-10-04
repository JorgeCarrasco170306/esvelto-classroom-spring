package com.esvelto.classroom.modules.students.dtos;

import java.util.List;

import com.esvelto.classroom.modules.auth.dtos.UserResponse;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;
import com.esvelto.classroom.modules.students.models.Student;

public record StudentResponse(
        UserResponse user,
        List<InstitutionResponse> institutions) {

    public static StudentResponse from(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentResponse(
                UserResponse.from(student.getUser()),
                student.getInstitutions() != null
                        ? student.getInstitutions().stream().map(InstitutionResponse::from).toList()
                        : List.of()
        );
    }
}
