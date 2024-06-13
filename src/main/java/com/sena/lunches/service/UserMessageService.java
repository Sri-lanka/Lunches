package com.sena.lunches.service;

import com.sena.lunches.entities.User_message;

import java.util.List;

public interface UserMessageService {

    public List<User_message> getUserMessage();

    public User_message saveUserMessage(User_message userMessage);

    public User_message getUserMessageById(Integer id);

    public User_message updateUserMessage(Integer id, User_message userMessage);

    public void deleteUserMessage(Integer id);
}
