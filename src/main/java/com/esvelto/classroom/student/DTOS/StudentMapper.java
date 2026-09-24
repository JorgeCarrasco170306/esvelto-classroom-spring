package com.esvelto.classroom.student.DTOS;

import com.esvelto.classroom.student.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mapping(source = "user.id", target = "userId")
    StudentResponseDTO toDto(Student student);

//    Student toEntity(StudentRequestDTO dto);

}
