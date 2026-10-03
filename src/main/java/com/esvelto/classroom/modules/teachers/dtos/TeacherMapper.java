package com.esvelto.classroom.modules.teachers.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.esvelto.classroom.modules.auth.dtos.AuthMapper;
import com.esvelto.classroom.modules.institutions.dtos.InstitutionMapper;
import com.esvelto.classroom.modules.teachers.models.Teacher;

@Mapper(componentModel = "spring", uses = {
        AuthMapper.class,
        InstitutionMapper.class
})
public interface TeacherMapper {

    TeacherResponse toResponse(Teacher teacher);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "institutions", ignore = true)
    Teacher toEntity(TeacherRequest req);
}
