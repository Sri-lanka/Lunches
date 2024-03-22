package com.sena.lunches.controller;

import com.sena.lunches.entities.Archive;

import com.sena.lunches.service.ArchiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/archive")
public class ControllerArchive {
    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/listArchive")
    public String listUsers(Model model) {
        List<Archive> archiveData = archiveService.getArchive();
        model.addAttribute("archives", archiveData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newArchives")
    public String createNewUser(Model model){
        model.addAttribute("archive", new Archive());
        model.addAttribute("action","");
        return "admin/principal/newArchive";
    }

    @PostMapping("/newArchives")
    public String saveUserData (@ModelAttribute Archive archive){
        archiveService.saveArchive(archive);
        return "redirect:/archive/listArchive";
    }

    @GetMapping("/editArchive/{idArchive}")
    public String updateArchive (@PathVariable Integer idArchive, Model model){
        model.addAttribute("archive", archiveService.getArchiveById(idArchive) );
        model.addAttribute("action","/archive/editArchive/" + idArchive);
        return "admin/principal/newArchive";
    }

    @PostMapping("/editArchive/{idArchive}")
    public String updatingArchive (@PathVariable Integer idArchive,@ModelAttribute Archive archive){
        archiveService.updateArchive(idArchive, archive);
        return "redirect:/archive/listArchive";
    }

    @GetMapping("/delete/{idArchive}")
    public String deleteArchive (@PathVariable Integer idArchive){
        archiveService.deleteArchive(idArchive);
        return "redirect:/archive/listArchive";
    }

}