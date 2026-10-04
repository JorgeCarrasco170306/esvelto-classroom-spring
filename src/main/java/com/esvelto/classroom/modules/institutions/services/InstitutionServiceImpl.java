package com.esvelto.classroom.modules.institutions.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionRequest;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionStudentResponse;
import com.esvelto.classroom.modules.institutions.dtos.TeacherInstitutionResponse;
import com.esvelto.classroom.modules.institutions.models.Institution;
import com.esvelto.classroom.modules.institutions.repository.InstitutionRepository;
import com.esvelto.classroom.modules.students.models.Student;
import com.esvelto.classroom.modules.students.repository.StudentRepo;
import com.esvelto.classroom.modules.teachers.models.Teacher;
import com.esvelto.classroom.modules.teachers.repository.TeacherRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository repository;
    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;

    @Override
    public InstitutionResponse findById(UUID id) {
        return toResponse(
                this.repository.findById(id)
                        .orElseThrow(() -> GlobalError.NotFound("institution not found")));
    }

    @Override
    public Page<InstitutionResponse> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional
    public InstitutionResponse save(InstitutionRequest entity) {

        Teacher teacher = teacherRepo.findByUserId(entity.getUserId())
                .orElseThrow(() -> GlobalError.NotFound("teacher not found"));

        if (repository.existsByNameAndTeacherId(
                entity.getName(),
                teacher.getId())) {
            throw GlobalError.BadRequest(
                    "You already have an institution with this name");
        }

        var institution = toEntity(entity);

        institution.setTeacher(teacher);

        return toResponse(this.repository.save(institution));
    }

    @Override
    public void deleteById(UUID id) {

        Institution institution = repository.findById(id)
                .orElseThrow(() -> GlobalError.NotFound("institution not found"));

        for (Student student : institution.getStudents()) {
            student.getInstitutions().remove(institution);
        }

        repository.delete(institution);

    }

    @Override
    public Page<InstitutionResponse> findAll(Pageable pageable, String name) {
        return this.repository.findByNameContainingIgnoreCase(pageable, name)
                .map(this::toResponse);
    }

    @Override
    @Transactional
    public void addStudentToInstitution(UUID institutionId, UUID userId) {

        Institution institution = repository.findById(institutionId)
                .orElseThrow(() -> GlobalError.NotFound("institution not found"));

        Student student = studentRepo.findByUserId(userId)
                .orElseThrow(() -> GlobalError.NotFound("student not found"));

        if (student.getInstitutions().contains(institution)) {
            throw GlobalError.BadRequest("student already belongs to this institution");
        }

        student.getInstitutions().add(institution);
        institution.addStudent(student);
    }

    private Institution toEntity(InstitutionRequest entity) {
        if (entity == null) {
            return null;
        }
        Institution institution = new Institution();
        institution.setName(entity.getName());
        institution.setImageUrl(entity.getImageUrl());
        return institution;
    }

    private InstitutionResponse toResponse(Institution institution) {
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

        InstitutionResponse response = new InstitutionResponse();
        response.setId(institution.getId());
        response.setName(institution.getName());
        response.setImageUrl(institution.getImageUrl());
        response.setTeacher(teacherDto);
        response.setStudents(students);
        return response;
    }

}
