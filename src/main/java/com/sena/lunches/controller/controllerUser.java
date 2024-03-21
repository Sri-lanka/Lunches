package com.sena.lunches.controller;

import com.sena.lunches.entities.User_sena;
import com.sena.lunches.service.User_sena_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping({"/","/inicio"})
public class controllerUser {

    @Autowired
    private User_sena_service UserSenaService;

    @GetMapping("listUser")
    public String listUsers(Model model) {
        try {
            List<User_sena> users = UserSenaService.getUser_sena();
            model.addAttribute("users", users);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }

    @GetMapping("/addUser")
    public  String AddUser(Model model) {
        model.addAttribute("User_sena",new User_sena());
        model.addAttribute("action", "");
        return "admin/principal/newUser";

    }
    @PostMapping("/addUser")
    public String saveUserSena (@ModelAttribute User_sena userSena){
        UserSenaService.saveUser_sena(userSena);
        return "redirect:/listUse";
    }



}
