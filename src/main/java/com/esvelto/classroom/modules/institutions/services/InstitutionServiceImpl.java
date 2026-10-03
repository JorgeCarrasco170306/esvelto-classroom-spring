package com.esvelto.classroom.modules.institutions.services;

import java.util.List;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionMapper;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionRequest;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;
import com.esvelto.classroom.modules.institutions.repository.InstitutionRepository;
import com.esvelto.classroom.modules.students.models.Student;
import com.esvelto.classroom.modules.students.repository.StudentRepo;
import com.esvelto.classroom.modules.teachers.repository.TeacherRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository repository;
    private final InstitutionMapper mapper;
    private final TeacherRepo teacherRepo;
    private final StudentRepo studentRepo;

    @Override
    public InstitutionResponse findById(UUID id) {
        return mapper.toResponse(
                this.repository.findById(id)
                        .orElseThrow(() -> GlobalError.NotFound("institution not found")));
    }

    @Override
    public Page<InstitutionResponse> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Override
    @Transactional
    public InstitutionResponse save(InstitutionRequest entity) {

        if (repository.existsByNameAndTeacherId(
                entity.getName(),
                entity.getTeacherId())) {
            throw GlobalError.BadRequest(
                    "You already have an institution with this name");
        }

        var institution = this.mapper.toEntity(entity);

        var teacher = this.teacherRepo.findById(entity.getTeacherId())
                .orElseThrow(() -> GlobalError.NotFound("teacher not found"));

        institution.setTeacher(teacher);

        return mapper.toResponse(this.repository.save(institution));
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Page<InstitutionResponse> findAll(Pageable pageable, String name) {
        return this.repository.findByNameContainingIgnoreCase(pageable, name)
                .map(mapper::toResponse);
    }

}
