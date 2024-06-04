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
    public String verifProfessional(@RequestParam Integer document, @RequestParam String keyword){
        try {
            User_sena user=null;
            user=userSenaRepo.findByDocumentAndKeyword(document,keyword);
            if(user!=null){
                if(user.getRoles()==1){
                    return "redirect:/moduleUser/" + document;

                }if(user.getRoles()==2 || user.getRoles()==3){
                    return "redirect:/userSena/listUser";
                }else{
                    return "redirect:/login";
                }
            } else {
                return "redirect:/login";
            }
        }catch (Exception e){
            return "redirect:/login";
        }
    }
}
