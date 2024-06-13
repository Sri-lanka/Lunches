package com.sena.lunches.controller;

import com.sena.lunches.entities.User_message;
import com.sena.lunches.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userMessage")
public class ControllerUserMessage {
    @Autowired
    private UserMessageService userMessageService;

    @GetMapping("/listUserMessage")
    public String listUserMessage(Model model) {
        List<User_message> userMessageData = userMessageService.getUserMessage();
        model.addAttribute("currentEndpoint", "/userMessage/listUserMessage");
        model.addAttribute("userMessageData", userMessageData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newUserMessage")
    public String addUserMessage(Model model){
        model.addAttribute("userMessage", new User_message());
        model.addAttribute("action","");
        return "admin/principal/Forms/newUserMessage";
    }

    @PostMapping("/newUserMessage")
    public String saveUserMessage(@ModelAttribute User_message userMessage){
        userMessageService.saveUserMessage(userMessage);
        return "redirect:/userMessage/listUserMessage";
    }

    @GetMapping("/editUserMessage/{idUserMessage}")
    public String updateUserMessage (@PathVariable Integer idUserMessage, Model model){
        model.addAttribute("userMessage", userMessageService.getUserMessageById(idUserMessage) );
        model.addAttribute("action","/userMessage/editUserMessage/" + idUserMessage);
        return "admin/principal/Forms/newUserMessage";
    }

    @PostMapping("/editUserMessage/{idUserMessage}")
    public String updatingUserMessage (@PathVariable Integer idUserMessage,@ModelAttribute User_message userMessage){
        userMessageService.updateUserMessage(idUserMessage, userMessage);
        return "redirect:/userMessage/listUserMessage";
    }

    @GetMapping("/delete/{idUserMessage}")
    public String deleteUserMessage (@PathVariable Integer idUserMessage){
        userMessageService.deleteUserMessage(idUserMessage);
        return "redirect:/userMessage/listUserMessage";
    }
}
