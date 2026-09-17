package com.esvelto.classroom.students.DTOS;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record StudentRequest
        (
                @NotBlank
                UUID userId
        ) {

}

