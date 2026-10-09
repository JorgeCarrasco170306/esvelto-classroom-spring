package com.esvelto.classroom.modules.courses.dto;

import com.esvelto.classroom.modules.courses.models.Course;

import java.util.List;
import java.util.UUID;

public record CourseResponse(
        UUID id,
        String name,
        CourseInstitutionResponse institution,
        List<CourseStudentResponse> students
) {

    public static CourseResponse from(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getName(),
                new CourseInstitutionResponse(
                        course.getInstitution().getId(),
                        course.getInstitution().getName()),
                course.getStudents().stream()
                        .map(CourseStudentResponse::from)
                        .toList()

        );
    }
}
