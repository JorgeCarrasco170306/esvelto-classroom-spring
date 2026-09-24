package com.esvelto.classroom.teacher.service;

import com.esvelto.classroom.auth.models.Role;
import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.teacher.DTOS.TeacherMapper;
import com.esvelto.classroom.teacher.DTOS.TeacherRequestDTO;
import com.esvelto.classroom.teacher.DTOS.TeacherResponseDTO;
import com.esvelto.classroom.teacher.models.Teacher;
import com.esvelto.classroom.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final UserRepository userRepository;

    @Override
    public Page<TeacherResponseDTO> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable).map(teacherMapper::toDto);
    }

    @Override
    public TeacherResponseDTO findById(UUID uuid) {
        Teacher teacher = teacherRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "teacher not found"));

        return teacherMapper.toDto(teacher);
    }

    @Override
    @Transactional
    public TeacherResponseDTO create(TeacherRequestDTO requestDto) {
        if (teacherRepository.existsByUser_Id(requestDto.userId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already is a teacher");
        }

        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        user.setRole(Role.TEACHER);
        userRepository.save(user);

        Teacher teacher = new Teacher();
        teacher.setUser(user);

        Teacher savedTeacher = teacherRepository.save(teacher);

        return teacherMapper.toDto(savedTeacher);
    }

    @Override
    public TeacherResponseDTO update(UUID uuid, TeacherRequestDTO requestDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        teacherRepository.deleteById(uuid);
    }
}
