package com.sena.lunches.controller;


import com.sena.lunches.entities.Excuse;
import com.sena.lunches.service.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/excuse")
public class controllerExcuse {
    @Autowired
    private ExcuseService excuseService;

    @GetMapping("/listExcuse")
    public String listUsers(Model model) {
        List<Excuse> excuseData = excuseService.getExcuse();
        model.addAttribute("excuse", excuseData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newExcuse")
    public String createNewUser(Model model){
        model.addAttribute("excuse", new Excuse());
        model.addAttribute("action","");
        return "admin/principal/Forms/newExcuse";
    }

    @PostMapping("/newExcuse")
    public String saveUserData (@ModelAttribute Excuse excuse){
        excuseService.saveExcuse(excuse);
        return "redirect:/excuse/listExcuse";
    }

    @GetMapping("/editExcuse/{idExcuse}")
    public String updateExcuse(@PathVariable Integer idExcuse, Model model){
        model.addAttribute("excuse", excuseService.getExcuseById(idExcuse) );
        model.addAttribute("action","/excuse/editExcuse/" + idExcuse);
        return "admin/principal/Forms/newExcuse";
    }

    @PostMapping("/editExcuse/{idExcuse}")
    public String updatingExcuse (@PathVariable Integer idExcuse,@ModelAttribute Excuse excuse){
        excuseService.updateExcuse(idExcuse, excuse);
        return "redirect:/excuse/listExcuse";
    }

    @GetMapping("/delete/{idExcuse}")
    public String deleteExcuse (@PathVariable Integer idExcuse){
        excuseService.deleteExcuse(idExcuse);
        return "redirect:/excuse/listBenefit";
    }


}
