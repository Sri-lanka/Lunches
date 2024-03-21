package com.sena.lunches.controller;



import com.sena.lunches.entities.User_file;
import com.sena.lunches.repository.User_file_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userFile")
public class controllerUserFile {
    @Autowired
    private User_file_sena_repo User_file_sena_repo;

    @GetMapping("/listUserFile")
    public String listUserFile(Model model) {
        try{
        List<User_file> UserFile = User_file_sena_repo.findAll();
        model.addAttribute("User_File", UserFile);}
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }
}
