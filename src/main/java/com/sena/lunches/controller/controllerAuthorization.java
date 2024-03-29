package com.sena.lunches.controller;

import com.sena.lunches.entities.Authorization;
import com.sena.lunches.entities.Authorization;
import com.sena.lunches.repository.Authorization_sena_repo;
import com.sena.lunches.service.AuthorizationService;
import com.sena.lunches.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/authorization")
public class controllerAuthorization {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/listAuthorization")
    public String listUsers(Model model) {
        List<Authorization> authorizationData = authorizationService.getAuthorization();
        model.addAttribute("authorizations", authorizationData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newAuthorizations")
    public String createNewUser(Model model){
        model.addAttribute("authorization", new Authorization());
        model.addAttribute("action","");
        return "admin/principal/newAuthorization";
    }

    @PostMapping("/newAuthorizations")
    public String saveUserData (@ModelAttribute Authorization authorization){
        authorizationService.saveAuthorization(authorization);
        return "redirect:/authorization/listAuthorization";
    }

    @GetMapping("/editAuthorization/{idAuthorization}")
    public String updateAuthorization (@PathVariable Integer idAuthorization, Model model){
        model.addAttribute("authorization", authorizationService.getAuthorizationById(idAuthorization) );
        model.addAttribute("action","/authorization/editAuthorization/" + idAuthorization);
        return "admin/principal/newAuthorization";
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
