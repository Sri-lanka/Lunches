package com.sena.lunches.controller;

import com.sena.lunches.entities.Message;
import com.sena.lunches.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/message")
public class ControllerMessage {
    @Autowired
    private MessageService messageService;

    @GetMapping("/listMessage")
    public String listUsers(Model model) {
        List<Message> messageData = messageService.getMessage();
        model.addAttribute("message", messageData);
        return "admin/principal/list-users";
    }

    @GetMapping("/newMessage")
    public String createNewUser(Model model){
        model.addAttribute("message", new Message());
        model.addAttribute("action","");
        return "admin/principal/Forms/newMessage";
    }

    @PostMapping("/newMessage")
    public String saveUserData (@ModelAttribute Message message){
        messageService.saveMessage(message);
        return "redirect:/message/listMessage";
    }

    @GetMapping("/editMessage/{idMessage}")
    public String updateMessage (@PathVariable Integer idMessage, Model model){
        model.addAttribute("message", messageService.getMessageById(idMessage) );
        model.addAttribute("action","/message/editMessage/" + idMessage);
        return "admin/principal/Forms/newMessage";
    }

    @PostMapping("/editMessage/{idMessage}")
    public String updatingMessage (@PathVariable Integer idMessage,@ModelAttribute Message message){
        messageService.updateMessage(idMessage, message);
        return "redirect:/message/listMessage";
    }

    @GetMapping("/delete/{idMessage}")
    public String deleteMessage (@PathVariable Integer idMessage){
        messageService.deleteMessage(idMessage);
        return "redirect:/message/listMessage";
    }
}
