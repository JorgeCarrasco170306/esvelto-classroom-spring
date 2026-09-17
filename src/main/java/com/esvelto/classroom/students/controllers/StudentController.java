package com.esvelto.classroom.students.controllers;

import com.esvelto.classroom.students.DTOS.StudentRequest;
import com.esvelto.classroom.students.DTOS.StudentRequestAddInstitutionDTO;
import com.esvelto.classroom.students.DTOS.StudentResponse;
import com.esvelto.classroom.students.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping()
    public ResponseEntity<Page<StudentResponse>> findAll(
            @PageableDefault(size = 10, sort = "studentCode") Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<StudentResponse> create(@RequestBody @Valid StudentRequest dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PatchMapping()
    public ResponseEntity<StudentResponse> addToInstitution(@RequestBody @Valid StudentRequestAddInstitutionDTO dto){
        return ResponseEntity.ok(service.addStudentToInstitution(dto));
    }


}
