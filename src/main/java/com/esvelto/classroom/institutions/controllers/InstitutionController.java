package com.esvelto.classroom.institutions.controllers;

import com.esvelto.classroom.institutions.DTOS.InstitutionRequest;
import com.esvelto.classroom.institutions.DTOS.InstitutionResponse;
import com.esvelto.classroom.institutions.services.InstitutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/institution")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService service;

    @GetMapping()
    public ResponseEntity<Page<InstitutionResponse>> findAll(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping()
    public ResponseEntity<InstitutionResponse> create(@Valid @RequestBody InstitutionRequest dto) {
        return ResponseEntity.ok(service.create(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
