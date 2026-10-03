package com.esvelto.classroom.modules.students.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {

    @NotNull
    private UUID userId;
}
