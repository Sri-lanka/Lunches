package com.sena.lunches.controller;

import com.sena.lunches.entities.User_sena;
import com.sena.lunches.service.UserSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/moduleUser")
public class ControllerModuleUser {

    @Autowired
    private UserSenaService userSenaService;
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

    @GetMapping("/editUser/{idUserSena}")
    public String updateUser (@PathVariable Integer idUserSena, Model model){
        model.addAttribute("userSena", userSenaService.getUser_senaById(idUserSena) );
        model.addAttribute("action","/editUser/" + idUserSena);
        return "UserModule/apprentice/formU/updateUser";
    }

    @PostMapping("/editUser/{idUserSena}")
    public String updatingUser(@PathVariable Integer idUserSena,@ModelAttribute User_sena userSena){
        userSenaService.updateUser_sena(idUserSena, userSena);
        return "redirect:/";
    }

}
