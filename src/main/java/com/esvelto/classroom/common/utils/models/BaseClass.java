package com.esvelto.classroom.common.utils.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data 
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
