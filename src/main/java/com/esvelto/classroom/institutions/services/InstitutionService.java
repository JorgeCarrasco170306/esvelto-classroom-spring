package com.esvelto.classroom.institutions.services;

import com.esvelto.classroom.common.utils.BaseClass;
import com.esvelto.classroom.institutions.DTOS.InstitutionRequest;
import com.esvelto.classroom.institutions.DTOS.InstitutionResponse;
import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.institutions.repository.InstitutionRepository;
import com.esvelto.classroom.students.models.Student;
import com.esvelto.classroom.students.repository.StudentRepository;
import com.esvelto.classroom.teachers.models.Teacher;
import com.esvelto.classroom.teachers.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository repository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Page<InstitutionResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(x -> new InstitutionResponse(
                        x.getName(),
                        x.getLogoUrl(),
                        x.getTeacher().getId(),
                        x.getStudents() != null ? x.getStudents().stream().map(BaseClass::getId).toList() : null
                ));
    }

    public InstitutionResponse create(InstitutionRequest dto) {

        Institution institution = new Institution();

        Teacher teacher = teacherRepository.findById((dto.teacherId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        if (repository.existsByName(dto.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Institution name already exists");
        }

        if (dto.studentsIds() != null) {
                List<Student> students = studentRepository.findAllById((dto.studentsIds()));
                students.forEach(x -> institution.getStudents().add(x));
        }

        institution.setName(dto.name());
        institution.setLogoUrl(dto.logoUrl());
        institution.setTeacher(teacher);

        repository.save(institution);

        return new InstitutionResponse(
                institution.getName(),
                institution.getLogoUrl(),
                institution.getTeacher().getId(),
                institution.getStudents().stream().map(BaseClass::getId).toList()
        );

    }

    public InstitutionResponse getById(UUID id) {

        var institution = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "institution not found"));

        return new InstitutionResponse(
                institution.getName(),
                institution.getLogoUrl(),
                institution.getTeacher().getId(),
                institution.getStudents().stream().map(BaseClass::getId).toList()
        );
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
