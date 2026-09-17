package com.esvelto.classroom.teachers.services;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.common.utils.BaseClass;
import com.esvelto.classroom.teachers.DTOS.TeacherRequest;
import com.esvelto.classroom.teachers.DTOS.TeacherResponse;
import com.esvelto.classroom.teachers.models.Teacher;
import com.esvelto.classroom.teachers.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final UserRepository userRepository;

    public Page<TeacherResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(
                x -> new TeacherResponse(
                        x.getId(),
                        x.getTeacherCode(),
                        x.getInstitutions().stream().map(BaseClass::getId).toList(),
                        x.getUser().getId()
                )
        );
    }

    public TeacherResponse create(TeacherRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id" + dto.userId() + "not found"));

        if (repository.existsByUserId(dto.userId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this user already is a teacher");
        }

        String code = user.getName().substring(0, 2) + user.getLastname().substring(0, 2)
                + "_" + user.getId().toString().substring(0, 5).toUpperCase();

        Teacher teacher = new Teacher();
        teacher.setTeacherCode(code);
        teacher.setUser(user);
        repository.save(teacher);

        return new TeacherResponse(
                teacher.getId(),
                teacher.getTeacherCode(),
                null,
                teacher.getUser().getId()
        );
    }


}
