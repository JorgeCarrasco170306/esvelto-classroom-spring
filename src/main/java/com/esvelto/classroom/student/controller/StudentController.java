package com.esvelto.classroom.student.controller;

import com.esvelto.classroom.student.DTOS.StudentRequestDTO;
import com.esvelto.classroom.student.DTOS.StudentResponseDTO;
import com.esvelto.classroom.student.service.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl service;


    @GetMapping
    public ResponseEntity<Page<StudentResponseDTO>> findAll(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> find(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(
            @Valid @RequestBody StudentRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}
