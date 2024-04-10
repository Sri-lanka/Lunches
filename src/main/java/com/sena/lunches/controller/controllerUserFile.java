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

    @GetMapping("/listUserFile")
    public String listUsers(Model model) {
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("User_File", user_fileData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newUserFile")
    public String createNewUser(Model model){
        model.addAttribute("user_file", new User_file());
        model.addAttribute("action","");
        return "admin/principal/newUserFile";
    }

    @PostMapping("/newUserFile")
    public String saveUserData (@ModelAttribute User_file user_file){
        userFileService.saveUser_file(user_file);
        return "redirect:/userFile/listUserFile";
    }

    @GetMapping("/editUserFile/{idUserFile}")
    public String updateUser_file (@PathVariable Integer idUserFile, Model model){
        model.addAttribute("user_file", userFileService.getUser_fileById(idUserFile) );
        model.addAttribute("action","/userFile/idUserFile/" + idUserFile);
        return "admin/principal/newUserFile";
    }

    @PostMapping("/editUserFile/{idUserFile}")
    public String updatingUser_file (@PathVariable Integer idUserFile,@ModelAttribute User_file user_file){
        userFileService.updateUser_file(idUserFile, user_file);
        return "redirect:/userFile/listUserFile";
    }

    @GetMapping("/delete/{idUserFile}")
    public String deleteUser_file (@PathVariable Integer idUserFile){
        userFileService.deleteUser_file(idUserFile);
        return "redirect:/userFile/listUserFile";
    }
}
