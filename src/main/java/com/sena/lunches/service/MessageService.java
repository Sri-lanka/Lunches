package com.sena.lunches.service;

import com.sena.lunches.entities.Message;

import java.util.List;

public interface MessageService {

    public List<Message> getMessage();

    public Message saveMessage(Message message);

    public Message getMessageById(Integer id);

    public Message updateMessage(Integer id, Message message);

    public void deleteMessage(Integer id);
}
