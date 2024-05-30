package com.sena.lunches.controller;

import com.sena.lunches.entities.User_sena;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.entities.File_sena;
import com.sena.lunches.entities.User_file;
import com.sena.lunches.entities.User_sena;
import com.sena.lunches.repository.User_sena_repo;
import com.sena.lunches.service.ArchiveService;
import com.sena.lunches.service.FileService;
import com.sena.lunches.service.UserFileService;
import com.sena.lunches.service.UserSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/moduleUser")
public class ControllerModuleUser {

    @Autowired
    private UserSenaService userSenaService;


    @Autowired
    private UserFileService userFileService;
    @Autowired
    private FileService fileService;
    @Autowired
    private  ArchiveService archiveService;


    @GetMapping
    public  String homeUser(Model model){
        List<User_sena> users = userSenaService.getUser_sena();
        model.addAttribute("users", users);
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("User_File", user_fileData);
        List<File_sena> file_senaData = fileService.getFile_sena();
        model.addAttribute("file", file_senaData);
        return "UserModule/apprentice/home";

    }

    @GetMapping("/userData")
    public String dataUser(Model model) {
        List<User_sena> users = userSenaService.getUser_sena();
        model.addAttribute("users", users);
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("User_File", user_fileData);
        List<File_sena> file_senaData = fileService.getFile_sena();
        model.addAttribute("file", file_senaData);
        List<Archive> listArchives = archiveService.getArchive();
        model.addAttribute("Archive", listArchives);
        return "UserModule/apprentice/data";
    }

    @GetMapping("/userHistory")
    public String historyUser(Model model) {
        List<User_sena> users = userSenaService.getUser_sena();
        model.addAttribute("users", users);
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("User_File", user_fileData);
        List<File_sena> file_senaData = fileService.getFile_sena();
        model.addAttribute("file", file_senaData);
        return "UserModule/apprentice/history";
    }

    @GetMapping("/updateUser/{idUserSena}")
    public String updateUser (@PathVariable Integer idUserSena, Model model){
        model.addAttribute("userSena", userSenaService.getUser_senaById(idUserSena) );
        model.addAttribute("action","/updateUser/" + idUserSena);
        return "UserModule/apprentice/formU/updateUser";
    }
    @PostMapping("/userData/{idUserSena}")
    public String updatingUser(@PathVariable Integer idUserSena, @ModelAttribute User_sena userSena){
        userSenaService.updateUser_sena(idUserSena, userSena);
        return "redirect:/";
    }



}
