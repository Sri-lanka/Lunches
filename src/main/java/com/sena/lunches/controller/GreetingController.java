package com.sena.lunches.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello api";
    }

    @GetMapping("/sayHelloSecurity")
    public String sayHelloSecurity(){
        return "hello api Security";
    }
}
