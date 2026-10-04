package com.esvelto.classroom.modules.teachers.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.auth.models.Role;
import com.esvelto.classroom.modules.auth.models.User;
import com.esvelto.classroom.modules.auth.repository.UserRepository;
import com.esvelto.classroom.modules.teachers.dtos.TeacherRequest;
import com.esvelto.classroom.modules.teachers.dtos.TeacherResponse;
import com.esvelto.classroom.modules.teachers.models.Teacher;
import com.esvelto.classroom.modules.teachers.repository.TeacherRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final UserRepository userRepository;

    @Override
    public TeacherResponse findById(UUID id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> GlobalError.NotFound("teacher not found"));

        return TeacherResponse.from(teacher);
    }

    @Override
    public Page<TeacherResponse> findAll(Pageable pageable) {
        return teacherRepo.findAll(pageable)
                .map(TeacherResponse::from);
    }

    @Override
    public TeacherResponse save(TeacherRequest entity) {

        if (teacherRepo.existsByUserId(entity.getUserId()))
            throw GlobalError.Conflict("this user is already a teacher");

        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> GlobalError.NotFound("user not found"));

        Teacher teacher = new Teacher();
        teacher.setUser(user);
        user.setRole(Role.TEACHER);

        teacherRepo.save(teacher);
        userRepository.save(user);

        return TeacherResponse.from(teacher);

    }

    @Override
    public void deleteById(UUID id) {
        teacherRepo.deleteById(id);
    }

}
