package com.sena.lunches.service;

import com.sena.lunches.entities.User_file;

import java.util.List;

public interface UserFileService {
    public List<User_file> getUser_file();

    public User_file saveUser_file(User_file user_file);

    public User_file getUser_fileById(Integer id);

    public User_file updateUser_file(Integer id, User_file user_file);

    public void deleteUser_file(Integer id);
}
