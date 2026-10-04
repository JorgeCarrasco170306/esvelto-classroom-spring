package com.esvelto.classroom.modules.courses.services;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.courses.dto.CourseRequest;
import com.esvelto.classroom.modules.courses.dto.CourseResponse;
import com.esvelto.classroom.modules.courses.models.Course;
import com.esvelto.classroom.modules.courses.repository.CourseRepository;
import com.esvelto.classroom.modules.institutions.models.Institution;
import com.esvelto.classroom.modules.institutions.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final InstitutionRepository institutionRepository;

    @Override
    public CourseResponse findById(UUID id) {
        var course = this.repository.findById(id)
                .orElseThrow(() -> GlobalError.NotFound("course not found"));

        return CourseResponse.from(course);
    }

    @Override
    public Page<CourseResponse> findAll(Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(CourseResponse::from);
    }

    @Override
    public CourseResponse save(CourseRequest entity) {
        Institution institution = institutionRepository.findById(entity.institutionId())
                .orElseThrow(() -> GlobalError.NotFound("institution not found"));

        var course = new Course();
        course.setName(entity.name());
        course.setInstitution(institution);

        repository.save(course);

        return CourseResponse.from(course);
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }
}
