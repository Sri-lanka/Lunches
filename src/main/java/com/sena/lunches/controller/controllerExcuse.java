package com.sena.lunches.controller;

import com.sena.lunches.entities.Excuse;
import com.sena.lunches.repository.Excuse_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/excuse")
public class controllerExcuse {
    @Autowired
    private Excuse_sena_repo excuse_sena_repo;

    @GetMapping("/listExcuse")
    public String listExcuse(Model model) {
        try{
        List<Excuse> Excuse = excuse_sena_repo.findAll();
        model.addAttribute("excuse", Excuse);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }


}
