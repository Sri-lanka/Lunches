package com.sena.lunches.controller;

import com.sena.lunches.entities.User_sena;
import com.sena.lunches.service.UserSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping({"/","/inicio"})
public class controllerUser {

    @Autowired
    private UserSenaService userSenaService;

    @GetMapping("listUser")
    public String listUsers(Model model) {
        try {
            List<User_sena> users = userSenaService.getUser_sena();
            model.addAttribute("users", users);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }

    @GetMapping("/newUsers")
    public  String AddUser(Model model) {
        model.addAttribute("User_sena",new User_sena());
        model.addAttribute("action", "");
        return "admin/principal/newUser";

    }
    @PostMapping("/newUsers")
    public String saveUserData (@ModelAttribute User_sena userSena){
        userSenaService.saveUser_sena(userSena);
        return "redirect:/listUser";
    }

    @GetMapping("/editUser/{idUserSena}")
    public String updateUser_sena (@PathVariable Integer idUserSena, Model model){
        model.addAttribute("User", userSenaService.getUser_senaById(idUserSena) );
        model.addAttribute("action","/listUser/editUser/" + idUserSena);
        return "admin/principal/newUser";
    }

    @PostMapping("/editUser/{idUserSena}")
    public String updatingUser_sena(@PathVariable Integer idUserSena,@ModelAttribute User_sena userSena){
        userSenaService.updateUser_sena(idUserSena, userSena);
        return "redirect:/listUser";
    }

    @GetMapping("/deleteUser/{idUserSena}")
    public String deleteUser_sena (@PathVariable Integer idUserSena){
        userSenaService.deleteUser_sena(idUserSena);
        return "redirect:/listUser";
    }





}
