package com.sena.lunches.service.impl;


import com.sena.lunches.entities.User_sena;
import com.sena.lunches.repository.User_sena_repo;
import com.sena.lunches.service.UserSenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserSenaServiceImpl implements UserSenaService {
    @Autowired
    private User_sena_repo userSenaRepo;

    @Override
    public List<User_sena> getUser_sena() {return userSenaRepo.findAll();}

    @Override
    public User_sena saveUser_sena(User_sena userSena) {
        return userSenaRepo.save(userSena);
    }

    @Override
    public User_sena getUser_senaById(Integer id) {
        return userSenaRepo.findById(id).orElse(null);
    }

    @Override
    public User_sena updateUser_sena(Integer id, User_sena userSena) {
        User_sena oldUser_sena = userSenaRepo.findById(id).orElse(null);
        if (oldUser_sena != null){
            oldUser_sena.setDocument(userSena.getDocument());
            oldUser_sena.setRol(userSena.getRol());
            oldUser_sena.setType_document(userSena.getType_document());
            oldUser_sena.setName_1(userSena.getName_1());
            oldUser_sena.setName_2(userSena.getName_2());
            oldUser_sena.setLast_name_1(userSena.getLast_name_1());
            oldUser_sena.setLast_name_2(userSena.getLast_name_2());
            oldUser_sena.setAge(userSena.getAge());
            oldUser_sena.setEmail(userSena.getEmail());
            oldUser_sena.setTelephone(userSena.getTelephone());
            oldUser_sena.setKeyword(userSena.getKeyword());
            oldUser_sena.setState(userSena.getState());
            oldUser_sena.setId_benefit(userSena.getId_benefit());
            return userSenaRepo.save(oldUser_sena);
        }
        return null;
    }

    @Override
    public void deleteUser_sena(Integer id) {
        userSenaRepo.deleteById(id);
    }
}
