package com.esvelto.classroom.student.service;

import com.esvelto.classroom.auth.models.Role;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.student.DTOS.StudentMapper;
import com.esvelto.classroom.student.DTOS.StudentRequestDTO;
import com.esvelto.classroom.student.DTOS.StudentResponseDTO;
import com.esvelto.classroom.student.models.Student;
import com.esvelto.classroom.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;


    @Override
    public Page<StudentResponseDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(studentMapper::toDto);
    }

    @Override
    public StudentResponseDTO findById(UUID uuid) {
        var student = repository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "student not found"));

        return studentMapper.toDto(student);
    }

    @Override
    @Transactional
    public StudentResponseDTO create(StudentRequestDTO requestDto) {
        if (repository.existsByUser_Id(requestDto.userId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already is a student");
        }

        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        user.setRole(Role.STUDENT);
        userRepository.save(user);

        var student = new Student();
        student.setUser(user);

        Student saved = repository.save(student);

        return studentMapper.toDto(saved);
    }

    @Override
    public StudentResponseDTO update(UUID uuid, StudentRequestDTO requestDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }
}
