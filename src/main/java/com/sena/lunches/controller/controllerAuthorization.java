package com.sena.lunches.controller;

import com.sena.lunches.entities.Authorization;
import com.sena.lunches.repository.Authorization_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping ("/authorization")
public class controllerAuthorization {

    @Autowired
    private Authorization_sena_repo authorization_sena_repo;

    @GetMapping("/listAuthorization")
    public String listAuthorization(Model model) {
        try {
            List<Authorization> Authorization = authorization_sena_repo.findAll();
            model.addAttribute("authorization", Authorization);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }
}
