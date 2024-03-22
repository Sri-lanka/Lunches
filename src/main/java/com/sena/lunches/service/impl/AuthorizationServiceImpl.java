package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Authorization;
import com.sena.lunches.repository.Authorization_sena_repo;
import com.sena.lunches.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private Authorization_sena_repo authorization_sena_repo;

    @Override
    public List<Authorization> getAuthorization() {return authorization_sena_repo.findAll();
    }

    @Override
    public Authorization saveAuthorization(Authorization authorization) {return authorization_sena_repo.save(authorization);
    }

    @Override
    public Authorization getAuthorizationById(Integer id) {return authorization_sena_repo.findById(id).orElse(null);
    }

    @Override
    public Authorization updateAuthorization(Integer id, Authorization authorization) {Authorization oldAuthorization = authorization_sena_repo.findById(id).orElse(null);
        if (oldAuthorization != null){
            oldAuthorization.setId_user(authorization. getId_user());
            oldAuthorization.setId_user_autho(authorization.getId_user_autho());
            oldAuthorization.setDescription_excuse(authorization.getDescription_excuse());
            oldAuthorization.setDate_apli(authorization.getDate_apli());

            return authorization_sena_repo.save(oldAuthorization);
        }
        return null;
    }

    @Override
    public void deleteAuthorization(Integer id) {authorization_sena_repo.deleteById(id);
    }
    
}
