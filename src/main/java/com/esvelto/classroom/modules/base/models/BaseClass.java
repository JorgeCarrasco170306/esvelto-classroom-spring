package com.esvelto.classroom.modules.base.models;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter 
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
