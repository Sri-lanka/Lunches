package com.sena.lunches.controller;

import com.sena.lunches.entities.Authorization;
import com.sena.lunches.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/authorization")
public class ControllerAuthorization {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/listAuthorization")
    public String listUsers(Model model) {
        List<Authorization> authorizationData = authorizationService.getAuthorization();
        model.addAttribute("authorization", authorizationData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newAuthorization")
    public String createNewUser(Model model){
        model.addAttribute("authorization", new Authorization());
        model.addAttribute("action","");
        return "admin/principal/Forms/newAuthorization";
    }

    @PostMapping("/newAuthorization")
    public String saveUserData (@ModelAttribute Authorization authorization){
        authorizationService.saveAuthorization(authorization);
        return "redirect:/authorization/listAuthorization";
    }

    @GetMapping("/editAuthorization/{idAuthorization}")
    public String updateAuthorization (@PathVariable Integer idAuthorization, Model model){
        model.addAttribute("authorization", authorizationService.getAuthorizationById(idAuthorization) );
        model.addAttribute("action","/authorization/editAuthorization/" + idAuthorization);
        return "admin/principal/Forms/newAuthorization";
    }

    @PostMapping("/editAuthorization/{idAuthorization}")
    public String updatingAuthorization (@PathVariable Integer idAuthorization,@ModelAttribute Authorization authorization){
        authorizationService.updateAuthorization(idAuthorization, authorization);
        return "redirect:/authorization/listAuthorization";
    }

    @GetMapping("/delete/{idAuthorization}")
    public String deleteAuthorization (@PathVariable Integer idAuthorization){
        authorizationService.deleteAuthorization(idAuthorization);
        return "redirect:/authorization/listAuthorization";
    }
}
