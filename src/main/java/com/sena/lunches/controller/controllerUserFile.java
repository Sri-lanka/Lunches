package com.sena.lunches.controller;

import com.sena.lunches.entities.User_file;
import com.sena.lunches.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userFile")
public class controllerUserFile {
    @Autowired
    private UserFileService userFileService;

    @GetMapping("/listUser_file")
    public String listUsers(Model model) {
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("user_file", user_fileData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newUser_files")
    public String createNewUser(Model model){
        model.addAttribute("user_file", new User_file());
        model.addAttribute("action","");
        return "admin/principal/newUser_file";
    }

    @PostMapping("/newUser_files")
    public String saveUserData (@ModelAttribute User_file user_file){
        userFileService.saveUser_file(user_file);
        return "redirect:/user_file/listUser_file";
    }

    @GetMapping("/editUser_file/{idUser_file}")
    public String updateUser_file (@PathVariable Integer idUser_file, Model model){
        model.addAttribute("user_file", userFileService.getUser_fileById(idUser_file) );
        model.addAttribute("action","/user_file/editUser_file/" + idUser_file);
        return "admin/principal/newUser_file";
    }

    @PostMapping("/editUser_file/{idUser_file}")
    public String updatingUser_file (@PathVariable Integer idUser_file,@ModelAttribute User_file user_file){
        userFileService.updateUser_file(idUser_file, user_file);
        return "redirect:/user_file/listUser_file";
    }

    @GetMapping("/delete/{idUser_file}")
    public String deleteUser_file (@PathVariable Integer idUser_file){
        userFileService.deleteUser_file(idUser_file);
        return "redirect:/user_file/listUser_file";
    }
}
