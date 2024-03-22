package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Excuse;
import com.sena.lunches.repository.Excuse_sena_repo;
import com.sena.lunches.service.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExcuseServiceImpl implements ExcuseService {

    @Autowired
    private Excuse_sena_repo excuse_sena_repo;

    @Override
    public List<Excuse> getExcuse() {return excuse_sena_repo.findAll();
    }

    @Override
    public Excuse saveExcuse(Excuse excuse) {
        return excuse_sena_repo.save(excuse);
    }

    @Override
    public Excuse getExcuseById(Integer id) {
        return excuse_sena_repo.findById(id).orElse(null);
    }

    @Override
    public Excuse updateExcuse(Integer id, Excuse  excuse) {
        Excuse oldExcuse = excuse_sena_repo.findById(id).orElse(null);
        if (oldExcuse != null){
            oldExcuse.setId_user(excuse.getId_user());
            oldExcuse.setId_assistance(excuse.getId_assistance());

            return excuse_sena_repo.save(oldExcuse);
        }
        return null;
    }

    @Override
    public void deleteExcuse(Integer id) {
        excuse_sena_repo.deleteById(id);
    }

}
