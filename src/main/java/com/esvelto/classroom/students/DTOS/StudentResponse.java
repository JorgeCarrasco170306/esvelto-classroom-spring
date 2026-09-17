package com.esvelto.classroom.students.DTOS;


import java.util.UUID;

public record StudentResponse
        (
                UUID id,
                String studentCode,
                UUID userId,
                UUID institutionId
        ) {

}
