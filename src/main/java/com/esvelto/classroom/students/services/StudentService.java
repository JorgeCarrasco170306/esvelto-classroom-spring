package com.esvelto.classroom.students.services;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.institutions.repository.InstitutionRepository;
import com.esvelto.classroom.students.DTOS.StudentRequest;
import com.esvelto.classroom.students.DTOS.StudentRequestAddInstitutionDTO;
import com.esvelto.classroom.students.DTOS.StudentResponse;
import com.esvelto.classroom.students.models.Student;
import com.esvelto.classroom.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;

    public Page<StudentResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(x -> new StudentResponse(
                x.getId(),
                x.getStudentCode(),
                x.getId(),
                x.getInstitution().getId()
        ));
    }

    public StudentResponse create(StudentRequest dto) {


        if (repository.existsByUserId(dto.userId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this user already is a student");
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id" + dto.userId() + "not found"));

        String code = user.getName().substring(0, 2) + user.getLastname().substring(0, 2)
                + "_" + user.getId().toString().substring(0, 5).toUpperCase();

        Student student = new Student();
        student.setUser(user);
        student.setStudentCode(code);
        student.setInstitution(null);
        repository.save(student);

        return new StudentResponse(

                student.getId(),
                student.getStudentCode(),
                student.getId(),
                null
        );
    }

    public StudentResponse addStudentToInstitution(StudentRequestAddInstitutionDTO dto) {

        Student student = repository.findById(dto.studentId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "student with id" + dto.studentId() + "not found"));

        Institution institution = institutionRepository.findById(dto.institutionId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "institution with id" + dto.institutionId() + "not found"));


        if (student.getInstitution().getId().equals(dto.institutionId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user already in institution " + dto.institutionId());
        }

        student.setInstitution(institution);
        repository.save(student);

        return new StudentResponse(

                student.getId(),
                student.getStudentCode(),
                student.getId(),
                student.getInstitution().getId()
        );


    }

}
