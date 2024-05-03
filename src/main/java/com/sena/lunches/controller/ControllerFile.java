package com.sena.lunches.controller;

import com.sena.lunches.entities.File_sena;
import com.sena.lunches.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/fileSena")
public class ControllerFile {

    @Autowired
    private FileService file_senaService;

    @GetMapping("/listFileSena")
    public String listFileSena(Model model) {
        List<File_sena> file_senaData = file_senaService.getFile_sena();
        model.addAttribute("file", file_senaData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newFileSena")
    public String addFileSena(Model model){
        model.addAttribute("file_sena", new File_sena());
        model.addAttribute("action","");
        return "admin/principal/Forms/newFile";
    }

    @PostMapping("/newFileSena")
    public String saveFileSena (@ModelAttribute File_sena file_sena){
        file_senaService.saveFile_sena(file_sena);
        return "redirect:/fileSena/listFileSena";
    }

    @GetMapping("/editFileSena/{idFileSena}")
    public String updateFileSena (@PathVariable Integer idFileSena, Model model){
        model.addAttribute("file_sena", file_senaService.getFile_senaById(idFileSena) );
        model.addAttribute("action","/fileSena/editFileSena/" + idFileSena);
        return "admin/principal/Forms/newFile";
    }

    @PostMapping("/editFileSena/{idFileSena}")
    public String updatingFileSena (@PathVariable Integer idFileSena,@ModelAttribute File_sena file_sena){
        file_senaService.updateFile_sena(idFileSena, file_sena);
        return "redirect:/fileSena/listFileSena";
    }

    @GetMapping("/delete/{idFileSena}")
    public String deleteFile_sena (@PathVariable Integer idFileSena){
        file_senaService.deleteFile_sena(idFileSena);
        return "redirect:/fileSena/listFileSena";
    }
}
