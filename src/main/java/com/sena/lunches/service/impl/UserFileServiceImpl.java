package com.sena.lunches.service.impl;

import com.sena.lunches.entities.User_file;
import com.sena.lunches.repository.User_file_sena_repo;
import com.sena.lunches.repository.User_file_sena_repo;
import com.sena.lunches.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    private User_file_sena_repo user_file_sena_repo;

    @Override
    public List<User_file> getUser_file() {return user_file_sena_repo.findAll();
    }

    @Override
    public User_file saveUser_file(User_file user_file) {return user_file_sena_repo.save(user_file);
    }

    @Override
    public User_file getUser_fileById(Integer id) {return user_file_sena_repo.findById(id).orElse(null);
    }

    @Override
    public User_file updateUser_file(Integer id, User_file user_file) {User_file oldUser_file = user_file_sena_repo.findById(id).orElse(null);
        if (oldUser_file != null){
            oldUser_file.setId_user(user_file. getId_user());
            oldUser_file.setId_user_autho(user_file.getId_user_autho());
            oldUser_file.setDescription_excuse(user_file.getDescription_excuse());
            oldUser_file.setDate_apli(user_file.getDate_apli());

            return user_file_sena_repo.save(oldUser_file);
        }
        return null;
    }

    @Override
    public void deleteUser_file(Integer id) {user_file_sena_repo.deleteById(id);
    }
}
