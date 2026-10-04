package com.esvelto.classroom.modules.institutions.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esvelto.classroom.modules.institutions.dtos.InstitutionRequest;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionResponse;
import com.esvelto.classroom.modules.institutions.services.InstitutionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService service;

    @GetMapping
    public ResponseEntity<Page<InstitutionResponse>> findAll(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable, name == null ? "" : name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponse> find(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<InstitutionResponse> create(
            @Valid @RequestBody InstitutionRequest dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PatchMapping("/{institutionId}/students/{studentId}")
    public ResponseEntity<Void> addStudent(
            @PathVariable UUID institutionId,
            @PathVariable UUID studentId
    ) {
        service.addStudentToInstitution(institutionId, studentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
