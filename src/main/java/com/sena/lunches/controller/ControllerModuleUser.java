package com.sena.lunches.controller;

import com.sena.lunches.entities.*;


import com.sena.lunches.repository.Message_repo;
import com.sena.lunches.repository.User_sena_repo;
import com.sena.lunches.service.*;
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
    private User_sena_repo userSenaRepo;

    @Autowired
    private UserFileService userFileService;
    @Autowired
    private FileService fileService;
    @Autowired
    private  ArchiveService archiveService;

    @Autowired
    private MessageService messageService;
    @Autowired
    private Message_repo message_repo;

    @GetMapping("/home/{document}")
    public String ListUser(Model model, @PathVariable int document){
            User_sena userSena = userSenaRepo.findByDocument(document);
            List<User_file> user_fileData =userFileService.getUser_file();
            List<File_sena> file_senaData = fileService.getFile_sena();
            model.addAttribute("file", file_senaData);
            model.addAttribute("userFile", user_fileData);
            model.addAttribute("user",userSena);
            return "UserModule/apprentice/home";
    }


    @GetMapping("/userData/{document}")
    public String dataUser(Model model, @PathVariable int document) {

        User_sena userSena = userSenaRepo.findByDocument(document);
        model.addAttribute("user",userSena);
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("userFile", user_fileData);
        List<File_sena> file_senaData = fileService.getFile_sena();
        model.addAttribute("file", file_senaData);
        return "UserModule/apprentice/data";
    }

    @GetMapping("/userHistory/{document}")
    public String historyUser(Model model, @PathVariable int document,@RequestParam(value = "typeMessage", defaultValue = "1") int typeMessage) {
        User_sena userSena = userSenaRepo.findByDocument(document);
        model.addAttribute("user",userSena);
        List<User_file> user_fileData = userFileService.getUser_file();
        model.addAttribute("userFile", user_fileData);
        List<File_sena> file_senaData = fileService.getFile_sena();
        model.addAttribute("file", file_senaData);

        List<Message> messageData = messageService.findByTypeMessage(typeMessage);
        model.addAttribute("message", messageData);

        return "UserModule/apprentice/history";
    }

    @GetMapping("/updateUser/{idUserSena}")
    public String updateUser(@PathVariable Integer idUserSena, Model model){
        User_sena userSena = userSenaService.getUser_senaById(idUserSena);
        model.addAttribute("userSena", userSena);
        model.addAttribute("action","/moduleUser/updateUser/" + idUserSena);
        return "UserModule/apprentice/formU/updateUser";
    }
    @PostMapping("/updateUser/{idUserSena}")
    public String updatingUser(@PathVariable Integer idUserSena, @ModelAttribute User_sena userSena){
        userSenaService.updateUser_sena(idUserSena, userSena);
        int document = userSena.getDocument();
        return "redirect:/moduleUser/userData/" + document;
    }

}
