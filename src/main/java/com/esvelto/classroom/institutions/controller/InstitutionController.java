package com.esvelto.classroom.institutions.controller;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.institutions.DTO.InstitutionRequestDTO;
import com.esvelto.classroom.institutions.DTO.InstitutionResponseDTO;
import com.esvelto.classroom.institutions.service.InstitutionService;
import com.esvelto.classroom.institutions.service.InstitutionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/institutions")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
public class InstitutionController {

    private final InstitutionService service;

    @GetMapping
    public ResponseEntity<Page<InstitutionResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "name") Pageable pageable
    ) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InstitutionResponseDTO> create(
            @Valid @ModelAttribute InstitutionRequestDTO dto,
            @RequestPart("file") MultipartFile file,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(user, dto, file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionResponseDTO> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delte(@PathVariable UUID id) {
        service.delete(id);
    }
}
