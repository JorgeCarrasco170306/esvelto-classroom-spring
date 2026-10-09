package com.esvelto.classroom.modules.courses.dto;

import com.esvelto.classroom.modules.students.models.Student;

import java.util.UUID;

public record CourseStudentResponse(
        UUID id,
        String name,
        String lastname,
        String email
) {

    public static CourseStudentResponse from(Student student){
        return new CourseStudentResponse(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getLastname(),
                student.getUser().getEmail()
        );
    }
}
