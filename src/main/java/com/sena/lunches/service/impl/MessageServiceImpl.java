package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Message;
import com.sena.lunches.repository.Message_repo;
import com.sena.lunches.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageServiceImpl  implements MessageService {

    @Autowired
    private Message_repo message_repo;

    @Override
    public List<Message> getMessage() {
        return message_repo.findAll();
    }

    @Override
    public Message saveMessage(Message message) {
        return message_repo.save(message);
    }

    @Override
    public Message getMessageById(Integer id) {
        return message_repo.findById(id).orElse(null);
    }

    @Override
    public Message updateMessage(Integer id, Message message) {
        Message oldMessage = message_repo.findById(id).orElse(null);
        if (oldMessage != null){
            oldMessage.setId_user(message. getId_user());
            oldMessage.setDescription_message(message.getDescription_message());
            oldMessage.setDate_send(message.getDate_send());
            return message_repo.save(oldMessage);
        }
        return null;
    }

    @Override
    public void deleteMessage(Integer id) {
        message_repo.deleteById(id);
    }

}
