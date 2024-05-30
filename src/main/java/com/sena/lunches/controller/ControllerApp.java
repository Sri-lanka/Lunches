package com.sena.lunches.controller;

import com.sena.lunches.entities.User_sena;
import com.sena.lunches.repository.User_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class ControllerApp {
    @Autowired
    private User_sena_repo userSenaRepo;
    @GetMapping
    public String login(){
        return "index";
    }

    @PostMapping("/loginPro")
    public String verifProfessional(@RequestParam int document, @RequestParam String keyword){
        try {
            User_sena login=null;
            login=userSenaRepo.findByDocumentAndKeyword(document,keyword);

            if(login!=null){

                return "redirect:/moduleUser";

            } else {
                return "admin/logIn";
            }
        }catch (Exception e){
            return "admin/logIn";
        }
    }
}
