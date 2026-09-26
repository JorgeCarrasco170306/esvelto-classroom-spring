package com.esvelto.classroom.curso.DTO;

import com.esvelto.classroom.curso.models.Curso;
import com.esvelto.classroom.institutions.models.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CursoMapper {

    @Mapping(target = "institutionsId", source = "institutions")
    CursoResponseDTO toDto(Curso curso);

    @Mapping(target = "institutions", ignore = true)
    Curso toEntity(CursoRequestDTO dto);

    default UUID mapInstitutionToId(Institution institution) {
        return institution != null ? institution.getId() : null;
    }

}
