package com.esvelto.classroom.modules.base.services;

import java.util.List;
import java.util.Optional;

/**
 * Contrato genérico base para operaciones CRUD y de persistencia.
 *
 * @param <R> El tipo de DTO que se retorna en las respuestas de lectura.
 * @param <T> El tipo de la entidad JPA principal asociada al repositorio.
 * @param <U> El tipo de dato de la clave primaria (ID) de la entidad (ej. Long,
 *            UUID).
 * 
 * @author Jorge
 * @version 1.0
 */
public interface GenericRepository<R, T, U> {

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id El identificador único de la entidad. No debe ser nulo.
     * @return Un {@link Optional} que contiene la respuesta DTO si existe, o vacío
     *         si no se encontró.
     */
    Optional<R> findById(U id);

    /**
     * Obtiene todos los registros disponibles mapeados a su DTO de salida.
     *
     * @return Una lista con todos los elementos encontrados.
     */
    List<R> findAll();

    /**
     * Guarda o actualiza una entidad en la base de datos.
     *
     * @param entity La entidad a persistir.
     * @return El DTO procesado con los datos guardados.
     * @throws IllegalArgumentException Si la entidad provista es nula.
     */
    R save(T entity);

    /**
     * Elimina un registro utilizando su identificador.
     *
     * @param id El identificador único del elemento a eliminar.
     */
    void deleteById(U id);
}