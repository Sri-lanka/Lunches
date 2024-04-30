package com.sena.lunches.controller;


import org.springframework.ui.Model;
import com.sena.lunches.entities.Archive;

import com.sena.lunches.service.ArchiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/archive")
public class ControllerArchive {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/listArchive")
    public String listUsers(Model model) {
        List<Archive> listArchives = archiveService.getArchive();
        model.addAttribute("Archive", listArchives);
        return "admin/principal/list-users";
    }

    @GetMapping("/newArchive")
    public String createNewUser(Model model){
        model.addAttribute("archive", new Archive());
        model.addAttribute("action","");
        return "admin/principal/Forms/newArchive";
    }


    @PostMapping("/newArchive")
    public String saveUserData(@RequestParam("file") MultipartFile file) throws IOException {;
        archiveService.store( file);
        return "redirect:/archive/listArchive";
    }
    @GetMapping("/editArchive/{idArchive}")
    public String updateArchive (@PathVariable Integer idArchive, Model model){
        model.addAttribute("archive", archiveService.getArchiveById(idArchive) );
        model.addAttribute("action","/archive/editArchive/" + idArchive);
        return "admin/principal/Forms/newArchive";
    }

    @PostMapping("/editArchive/{idArchive}")
    public String updatingArchive (@PathVariable Integer idArchive, @RequestParam("file") MultipartFile file) throws IOException {
        Archive updatedArchive = archiveService.updateArchive(idArchive, file);

        return "redirect:/archive/listArchive";
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        Archive archive = archiveService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archive.getName_archive() + "\"")
                .body(archive.getArchive_pdf());
    }

    @GetMapping("/delete/{idArchive}")
    public String deleteArchive (@PathVariable Integer idArchive){
        archiveService.deleteArchive(idArchive);
        return "redirect:/archive/listArchive";
    }
}