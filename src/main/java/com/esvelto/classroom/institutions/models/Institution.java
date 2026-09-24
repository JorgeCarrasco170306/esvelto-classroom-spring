package com.esvelto.classroom.institutions.models;

import com.esvelto.classroom.common.utils.models.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "institutions")
@Data
public class Institution extends Auditable {
    private String name;
    private String imageUrl;

}
