package com.esvelto.classroom.modules.institutions.dtos;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.esvelto.classroom.modules.institutions.models.Institution;
import com.esvelto.classroom.modules.students.models.Student;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "students", source = "students")
    InstitutionResponse toResponse(Institution institution);

    
    @Mapping (target = "id", ignore = true)
    @Mapping (target = "students", ignore = true)
    @Mapping (target = "teacher", ignore = true)
    Institution toEntity(InstitutionRequest req);

    default UUID map(Student student) {
        return student == null ? null : student.getId();
    }

}
