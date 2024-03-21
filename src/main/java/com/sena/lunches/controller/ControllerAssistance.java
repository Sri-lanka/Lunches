package com.sena.lunches.controller;

import com.sena.lunches.entities.Assistance;
import com.sena.lunches.repository.Assistance_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/assistance")
public class ControllerAssistance {
    @Autowired
    private Assistance_repo assistance_repo;
    @GetMapping("/listAssistance")
    public String listAssistance(Model model) {
        try {
            List<Assistance> assist = assistance_repo.findAll();
            model.addAttribute("assistance", assist);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }

}
