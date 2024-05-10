package com.sena.lunches.controller;

import com.sena.lunches.entities.Program;
import com.sena.lunches.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/program")
public class ControllerProgram {
    @Autowired
    private ProgramService programService;
    @GetMapping("/listProgram")
    public String listProgram(Model model) {
        List<Program> programsData = programService.getProgram();
        model.addAttribute("currentEndpoint", "/program/listProgram");
        model.addAttribute("program", programsData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newProgram")
    public String addProgram(Model model){
        model.addAttribute("program", new Program());
        model.addAttribute("action","");
        return "admin/principal/Forms/newProgram";
    }

    @PostMapping("/newProgram")
    public String saveProgram (@ModelAttribute Program program){
        programService.saveProgram(program);
        return "redirect:/program/listProgram";
    }

    @GetMapping("/editProgram/{idProgram}")
    public String updateProgram (@PathVariable Integer idProgram, Model model){
        model.addAttribute("program", programService.getProgramById(idProgram) );
        model.addAttribute("action","/program/editProgram/" + idProgram);
        return "admin/principal/Forms/newProgram";
    }

    @PostMapping("/editProgram/{idProgram}")
    public String updatingProgram (@PathVariable Integer idProgram,@ModelAttribute Program program){
        programService.updateProgram(idProgram, program);
        return "redirect:/program/listProgram";
    }

    @GetMapping("/delete/{idProgram}")
    public String deleteProgram (@PathVariable Integer idProgram){
        programService.deleteProgram(idProgram);
        return "redirect:/program/listProgram";
    }
}
