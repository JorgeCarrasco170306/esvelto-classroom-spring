package com.esvelto.classroom.modules.institutions.dtos;

import java.util.List;
import java.util.UUID;

import com.esvelto.classroom.modules.institutions.models.Institution;
import com.esvelto.classroom.modules.students.models.Student;
import com.esvelto.classroom.modules.teachers.models.Teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionResponse {

    private UUID id;
    private String name;
    private String imageUrl;
    private TeacherInstitutionResponse teacher;
    private List<InstitutionStudentResponse> students;

    public static InstitutionResponse from(Institution institution) {
        if (institution == null) {
            return null;
        }

        TeacherInstitutionResponse teacherDto = null;
        Teacher teacher = institution.getTeacher();
        if (teacher != null) {
            UUID teacherId = teacher.getId();
            UUID userId = teacher.getUser() != null ? teacher.getUser().getId() : null;
            String name = teacher.getUser() != null ? teacher.getUser().getName() : null;
            String lastname = teacher.getUser() != null ? teacher.getUser().getLastname() : null;
            String email = teacher.getUser() != null ? teacher.getUser().getEmail() : null;
            teacherDto = new TeacherInstitutionResponse(teacherId, userId, name, lastname, email);
        }

        List<InstitutionStudentResponse> students = (institution.getStudents() != null)
                ? institution.getStudents().stream().map(student -> {
                    UUID studentId = student.getId();
                    UUID userId = student.getUser() != null ? student.getUser().getId() : null;
                    String name = student.getUser() != null ? student.getUser().getName() : null;
                    String lastname = student.getUser() != null ? student.getUser().getLastname() : null;
                    String email = student.getUser() != null ? student.getUser().getEmail() : null;
                    return new InstitutionStudentResponse(studentId, userId, name, lastname, email);
                }).toList()
                : List.of();

        return InstitutionResponse.builder()
                .id(institution.getId())
                .name(institution.getName())
                .imageUrl(institution.getImageUrl())
                .teacher(teacherDto)
                .students(students)
                .build();
    }
}
