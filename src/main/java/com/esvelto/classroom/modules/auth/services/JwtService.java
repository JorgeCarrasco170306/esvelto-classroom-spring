package com.esvelto.classroom.modules.auth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long expiration;

    // ? obtener key
    // ? generar token
    // ? extraer el username del token
    // ? validar que sea valido
    // ? validar que no esté expirado
    // ? validar que no esté modificado

}
