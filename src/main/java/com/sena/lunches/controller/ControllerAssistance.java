package com.sena.lunches.controller;

import com.sena.lunches.entities.Assistance;
import com.sena.lunches.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assistance")
public class ControllerAssistance {
    @Autowired
    private AssistanceService assistanceService;

    @GetMapping("/listAssistance")
    public String listAssistance(Model model) {
        List<Assistance> assistanceData = assistanceService.getAssistance();
        model.addAttribute("currentEndpoint", "/assistance/listAssistance");
        model.addAttribute("assistance", assistanceData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newAssistance")
    public String addAssistance(Model model){
        model.addAttribute("assistance", new Assistance());
        model.addAttribute("action","");
        return "admin/principal/Forms/newAssistance";
    }

    @PostMapping("/newAssistance")
    public String saveAssistance (@ModelAttribute Assistance assistance){
        assistanceService.saveAssistance(assistance);
        return "redirect:/assistance/listAssistance";
    }

    @GetMapping("/editAssistance/{idAssistance}")
    public String updateAssistance (@PathVariable Integer idAssistance, Model model){
        model.addAttribute("assistance", assistanceService.getAssistanceById(idAssistance) );
        model.addAttribute("action","/assistance/editAssistance/" + idAssistance);
        return "admin/principal/Forms/newAssistance";
    }

    @PostMapping("/editAssistance/{idAssistance}")
    public String updatingAssistance (@PathVariable Integer idAssistance,@ModelAttribute Assistance assistance){
        assistanceService.updateAssistance(idAssistance, assistance);
        return "redirect:/assistance/listAssistance";
    }

    @GetMapping("/delete/{idAssistance}")
    public String deleteAssistance (@PathVariable Integer idAssistance){
        assistanceService.deleteAssistance(idAssistance);
        return "redirect:/assistance/listAssistance";
    }
}
