package com.sena.lunches.controller;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.service.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/benefit")
public class ControllerBenefit {
    @Autowired
    private BenefitService benefitService;

    @GetMapping("/listBenefit")
    public String listUsers(Model model) {
        List<Benefit> benefitData = benefitService.getBenefit();
        model.addAttribute("benefits", benefitData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newBenefits")
    public String createNewUser(Model model){
        model.addAttribute("benefit", new Benefit());
        model.addAttribute("action","");
        return "admin/principal/Forms/newBenefit";
    }

    @PostMapping("/newBenefits")
    public String saveUserData (@ModelAttribute Benefit benefit){
        benefitService.saveBenefit(benefit);
        return "redirect:/benefit/listBenefit";
    }

    @GetMapping("/editBenefit/{idBenefit}")
    public String updateBenefit (@PathVariable Integer idBenefit, Model model){
        model.addAttribute("benefit", benefitService.getBenefitById(idBenefit) );
        model.addAttribute("action","/benefit/editBenefit/" + idBenefit);
        return "admin/principal/Forms/newBenefit";
    }

    @PostMapping("/editBenefit/{idBenefit}")
    public String updatingBenefit (@PathVariable Integer idBenefit,@ModelAttribute Benefit benefit){
        benefitService.updateBenefit(idBenefit, benefit);
        return "redirect:/benefit/listBenefit";
    }

    @GetMapping("/delete/{idBenefit}")
    public String deleteBenefit (@PathVariable Integer idBenefit){
        benefitService.deleteBenefit(idBenefit);
        return "redirect:/benefit/listBenefit";
    }
}
