package com.esvelto.classroom.modules.auth.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.esvelto.classroom.modules.auth.models.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "verificationCode", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toEntity(RegisterRequest req);

}
