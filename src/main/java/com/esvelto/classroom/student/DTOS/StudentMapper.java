package com.esvelto.classroom.student.DTOS;

import com.esvelto.classroom.student.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "user.id", target = "userId")
    StudentResponseDTO toDto(Student student);

    default UUID toId(Student student) {
        return student != null ? student.getId() : null;
    }
}
