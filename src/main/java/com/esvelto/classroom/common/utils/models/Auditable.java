package com.esvelto.classroom.common.utils.models;

import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

public class Auditable extends BaseClass {
    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    private UUID modifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}
