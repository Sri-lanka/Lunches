package com.sena.lunches.controller;


import com.sena.lunches.entities.Message;
import com.sena.lunches.repository.Message_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/message")
public class ControllerMessage {
    @Autowired
    private Message_repo message_repo;

    @GetMapping("/listMessage")
    public String listMessage(Model model) {
        try {
            List<Message> Message = message_repo.findAll();
            model.addAttribute("message", Message);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "admin/principal/list-users";

    }
}
