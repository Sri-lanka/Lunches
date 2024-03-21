package com.sena.lunches.controller;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.repository.Archive_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/archive")
public class ControllerArchive {
    @Autowired
    private Archive_repo archive_repo;

    @GetMapping("/listArchive")
    public String listArchive(Model model) {
        try {
            List<Archive> Archive = archive_repo.findAll();
            model.addAttribute("Archive", Archive);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";
    }

}