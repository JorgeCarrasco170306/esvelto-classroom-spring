package com.esvelto.classroom.institutions.DTO;

import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.student.DTOS.StudentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { StudentMapper.class }
)
public interface InstitutionMapper {

    @Mapping(source = "students", target = "studentsIds")
    @Mapping(source = "teacher.id", target = "teacherId")
    InstitutionResponseDTO toDto(Institution institution);

}
