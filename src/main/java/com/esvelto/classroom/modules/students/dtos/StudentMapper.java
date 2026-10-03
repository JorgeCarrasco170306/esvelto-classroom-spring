package com.esvelto.classroom.modules.students.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.esvelto.classroom.modules.auth.dtos.AuthMapper;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionMapper;
import com.esvelto.classroom.modules.students.models.Student;

@Mapper(componentModel = "spring", uses = {
        AuthMapper.class,
        InstitutionMapper.class
})
public interface StudentMapper {

    StudentResponse toResponse(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "institutions", ignore = true)
    Student toEntity(StudentRequest req);
}
