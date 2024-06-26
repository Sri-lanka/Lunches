package com.sena.lunches.controller;

import com.sena.lunches.entities.User_sena;
import com.sena.lunches.service.UserSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/userSena")
public class ControllerUser {

    @Autowired
    private UserSenaService userSenaService;

    @GetMapping("/listUser")
    public String listUser(Model model) {
        List<User_sena> users = userSenaService.getUser_sena();
        model.addAttribute("currentEndpoint", "/userSena/listUser");
        model.addAttribute("users", users);
        return "admin/principal/list-users";
    }

    @GetMapping("/newUser")
    public  String addUser(Model model) {
        model.addAttribute("userSena",new User_sena());
        model.addAttribute("action", "");
        return "admin/principal/Forms/newUser";
    }
    @PostMapping("/newUser")
    public String saveUser (@ModelAttribute User_sena userSena){
        userSenaService.saveUser_sena(userSena);
        return "redirect:/userSena/listUser";
    }

    @GetMapping("/editUser/{idUserSena}")
    public String updateUser (@PathVariable Integer idUserSena, Model model){
        model.addAttribute("userSena", userSenaService.getUser_senaById(idUserSena) );
        model.addAttribute("action","/userSena/editUser/" + idUserSena);
        return "admin/principal/Forms/newUser";
    }

    @PostMapping("/editUser/{idUserSena}")
    public String updatingUser(@PathVariable Integer idUserSena,@ModelAttribute User_sena userSena){
        userSenaService.updateUser_sena(idUserSena, userSena);
        return "redirect:/userSena/listUser";
    }



    @GetMapping("/delete/{idUserSena}")
    public String deleteUser (@PathVariable Integer idUserSena){
        userSenaService.deleteUser_sena(idUserSena);
        return "redirect:/userSena/listUser";
    }

}
