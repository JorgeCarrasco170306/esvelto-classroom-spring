
package com.esvelto.classroom.modules.teachers.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class TeacherRequest {
    @NotNull 
    UUID userId;
}
