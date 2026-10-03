package com.esvelto.classroom.modules.students.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.auth.models.Role;
import com.esvelto.classroom.modules.auth.models.User;
import com.esvelto.classroom.modules.auth.repository.UserRepository;
import com.esvelto.classroom.modules.students.dtos.StudentMapper;
import com.esvelto.classroom.modules.students.dtos.StudentRequest;
import com.esvelto.classroom.modules.students.dtos.StudentResponse;
import com.esvelto.classroom.modules.students.models.Student;
import com.esvelto.classroom.modules.students.repository.StudentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse findById(UUID id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> GlobalError.NotFound("student not found"));

        return studentMapper.toResponse(student);
    }

    @Override
    public Page<StudentResponse> findAll(Pageable pageable) {
        return studentRepo.findAll(pageable)
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse save(StudentRequest entity) {
        if (studentRepo.existsByUserId(entity.getUserId())) {
            throw GlobalError.Conflict("this user is already a student");
        }

        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> GlobalError.NotFound("user not found"));

        Student student = studentMapper.toEntity(entity);
        student.setUser(user);
        user.setRole(Role.STUDENT);

        studentRepo.save(student);
        userRepository.save(user);

        return studentMapper.toResponse(student);
    }

    @Override
    public void deleteById(UUID id) {
        studentRepo.deleteById(id);
    }
}
