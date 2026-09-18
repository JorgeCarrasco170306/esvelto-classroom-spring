package com.esvelto.classroom.teachers.controllers;

import com.esvelto.classroom.teachers.DTOS.TeacherRequest;
import com.esvelto.classroom.teachers.DTOS.TeacherResponse;
import com.esvelto.classroom.teachers.services.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping()
    public ResponseEntity<Page<TeacherResponse>> findAll(
            @PageableDefault(size = 10, sort = "teacherCode") Pageable pageable
    ) {
        return ResponseEntity.ok(teacherService.findAll(pageable));
    }

    @PostMapping()
    public ResponseEntity<TeacherResponse> create(
            @Valid @RequestBody TeacherRequest dto
    ) {
        return ResponseEntity.ok(teacherService.create(dto));
    }


}
