package com.esvelto.classroom.teacher.DTOS;

import com.esvelto.classroom.teacher.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper {
    @Mapping(source = "user.id", target = "userId")
    TeacherResponseDTO toDto(Teacher teacher);
//    Teacher toEntity(TeacherRequestDTO teacher);
}
