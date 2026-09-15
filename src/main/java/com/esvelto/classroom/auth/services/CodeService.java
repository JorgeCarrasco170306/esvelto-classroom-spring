package com.esvelto.classroom.auth.services;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

public class CodeService {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateCode(){
        int number = 100000 + RANDOM.nextInt(900000);
        return String.valueOf(number);
    }

}
