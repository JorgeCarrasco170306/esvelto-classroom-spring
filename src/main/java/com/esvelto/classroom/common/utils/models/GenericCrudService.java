package com.esvelto.classroom.common.utils.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <D>  Response DTO (el que retorna la API)
 * @param <C>  Create/Update Request DTO (el que recibe la API para guardar/modificar)
 * @param <ID> Tipo del identificador (UUID, Long, etc.)
 */
public interface GenericCrudService<D, C, ID> {
    Page<D> findAll(Pageable pageable);

    D findById(ID id);

    D create(C requestDto);

    D update(ID id, C requestDto);

    void delete(ID id);
}