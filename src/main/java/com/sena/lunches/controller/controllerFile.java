package com.sena.lunches.controller;

import com.sena.lunches.entities.File_sena;
import com.sena.lunches.repository.File_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping ("/file")
public class controllerFile {

    @Autowired
    private File_sena_repo file_sena_repo;

    @GetMapping("/listFile")
    public String listFile(Model model) {
        try {
            List<File_sena> Files = file_sena_repo.findAll();
            model.addAttribute("file", Files);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }
}
