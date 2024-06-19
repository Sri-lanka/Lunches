package com.sena.lunches.service;

import com.sena.lunches.entities.Message;
import com.sena.lunches.repository.Message_repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface MessageService {


    public List<Message> getMessage();
    public List<Message> findByTypeMessage(int typeMessage);

    public Message saveMessage(Message message);

    public Message getMessageById(Integer id);

    public Message updateMessage(Integer id, Message message);

    public void deleteMessage(Integer id);
}
