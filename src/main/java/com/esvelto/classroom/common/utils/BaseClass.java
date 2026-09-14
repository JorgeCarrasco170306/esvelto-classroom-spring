package com.esvelto.classroom.common.utils;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface
@MappedSuperclass
@Getter
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
