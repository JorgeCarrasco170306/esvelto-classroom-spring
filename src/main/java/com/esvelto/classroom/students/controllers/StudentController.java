package com.esvelto.classroom.students.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
class StudentController {

    @GetMapping()
    public String hello(String message){
        return message + "hello";
    }

}
