package com.sena.lunches.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moduleUser")
public class ControllerModuleUser {

    @GetMapping
    public  String homeUser(){
        return "UserModule/apprentice/home";
    }

    @GetMapping("/userData")
    public String dataUser() {
        return "UserModule/apprentice/data";
    }

    @GetMapping("/userHistory")
    public String historyUser() {
        return "UserModule/apprentice/history";
    }

}
