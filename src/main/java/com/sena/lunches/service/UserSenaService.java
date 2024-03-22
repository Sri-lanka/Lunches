package com.sena.lunches.service;

import com.sena.lunches.entities.User_sena;
import java.util.List;


public interface UserSenaService {
    public List<User_sena> getUser_sena();

    public User_sena saveUser_sena(User_sena userSena);

    public User_sena getUser_senaById(Integer id);

    public User_sena updateUser_sena(Integer id, User_sena userSena);

    public void deleteUser_sena(Integer id);

}
