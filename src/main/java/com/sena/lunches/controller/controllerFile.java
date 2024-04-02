package com.sena.lunches.controller;

import com.sena.lunches.entities.File_sena;
import com.sena.lunches.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/file")
public class controllerFile {

    @Autowired
    private FileService file_senaService;

    @GetMapping("/listFile_sena")
    public String listUsers(Model model) {
        List<File_sena> file_senaData = file_senaService.getFile_sena();
        model.addAttribute("file", file_senaData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newFile_sena")
    public String createNewUser(Model model){
        model.addAttribute("file_sena", new File_sena());
        model.addAttribute("action","");
        return "admin/principal/newFile";
    }

    @PostMapping("/newFile_sena")
    public String saveUserData (@ModelAttribute File_sena file_sena){
        file_senaService.saveFile_sena(file_sena);
        return "redirect:/file_sena/listFile_sena";
    }

    @GetMapping("/editFile_sena/{idFile_sena}")
    public String updateFile_sena (@PathVariable Integer idFile_sena, Model model){
        model.addAttribute("file_sena", file_senaService.getFile_senaById(idFile_sena) );
        model.addAttribute("action","/file_sena/editFile_sena/" + idFile_sena);
        return "admin/principal/newFile";
    }

    @PostMapping("/editFile_sena/{idFile_sena}")
    public String updatingFile_sena (@PathVariable Integer idFile_sena,@ModelAttribute File_sena file_sena){
        file_senaService.updateFile_sena(idFile_sena, file_sena);
        return "redirect:/file_sena/listFile_sena";
    }

    @GetMapping("/delete/{idFile_sena}")
    public String deleteFile_sena (@PathVariable Integer idFile_sena){
        file_senaService.deleteFile_sena(idFile_sena);
        return "redirect:/file_sena/listFile_sena";
    }
}
