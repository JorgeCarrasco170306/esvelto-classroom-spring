package com.esvelto.classroom.modules.institutions.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionResponse {
    private UUID id;
    private String name;
    private String imageUrl;
    private List<UUID> students;
    private UUID teacherId;
}
