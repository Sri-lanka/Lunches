package com.sena.lunches.controller;

import com.sena.lunches.entities.Program;
import com.sena.lunches.repository.Program_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/program")
public class controllerProgram {
    @Autowired
    private Program_sena_repo program_sena_repo;
    @GetMapping("/listProgram")
    public String listProgram(Model model) {
        try{
        List<Program> Programs = program_sena_repo.findAll();
        model.addAttribute("program", Programs);}
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }
}
