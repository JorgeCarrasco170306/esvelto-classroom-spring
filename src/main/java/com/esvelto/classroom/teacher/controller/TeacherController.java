package com.esvelto.classroom.teacher.controller;

import com.esvelto.classroom.teacher.DTOS.TeacherRequestDTO;
import com.esvelto.classroom.teacher.DTOS.TeacherResponseDTO;
import com.esvelto.classroom.teacher.service.TeacherServiceImpl;
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
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    @GetMapping
    public ResponseEntity<Page<TeacherResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(teacherService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> create(@Valid @RequestBody TeacherRequestDTO requestDto) {
        TeacherResponseDTO response = teacherService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody TeacherRequestDTO requestDto) {
        return ResponseEntity.ok(teacherService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        teacherService.delete(id);
    }
}
