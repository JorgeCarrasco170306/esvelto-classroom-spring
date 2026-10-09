package com.esvelto.classroom.modules.homework.services;

import com.esvelto.classroom.modules.courses.repository.CourseRepository;
import com.esvelto.classroom.modules.homework.dtos.HomeworkRequest;
import com.esvelto.classroom.modules.homework.dtos.HomeworkResponse;
import com.esvelto.classroom.modules.homework.repository.HomeworkRepository;
import com.esvelto.classroom.modules.students.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private final CourseRepository courseRepository;
    private final StudentRepo studentRepo;
    private final HomeworkRepository repository;

    @Override
    public HomeworkResponse findById(UUID id) {
        return null;
    }

    @Override
    public Page<HomeworkResponse> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public HomeworkResponse save(HomeworkRequest entity) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
