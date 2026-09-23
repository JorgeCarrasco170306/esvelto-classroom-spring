package com.esvelto.classroom.auth.DTOS;

import com.esvelto.classroom.auth.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterDTO dto);
}
