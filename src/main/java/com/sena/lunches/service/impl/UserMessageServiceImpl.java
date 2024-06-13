package com.sena.lunches.service.impl;

import com.sena.lunches.entities.User_message;
import com.sena.lunches.repository.UserMessageRepo;
import com.sena.lunches.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageRepo userMessageRepo;

    @Override
    public List<User_message> getUserMessage() {
        return userMessageRepo.findAll();
    }

    @Override
    public User_message saveUserMessage(User_message userMessage) {
        return userMessageRepo.save(userMessage);
    }

    @Override
    public User_message getUserMessageById(Integer id) {
        return userMessageRepo.findById(id).orElse(null);
    }

    @Override
    public User_message updateUserMessage(Integer id, User_message userMessage) {
        User_message oldUserMessage = userMessageRepo.findById(id).orElse(null);
        if (oldUserMessage != null){
            oldUserMessage.setId_user(userMessage.getId_user());
            oldUserMessage.setId_message(userMessage.getId_message());
            oldUserMessage.setRecipient(userMessage.getRecipient());
            return userMessageRepo.save(oldUserMessage);
        }
        return null;
    }

    @Override
    public void deleteUserMessage(Integer id) {
        userMessageRepo.deleteById(id);
    }

}
