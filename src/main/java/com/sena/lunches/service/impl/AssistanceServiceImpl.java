package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Assistance;
import com.sena.lunches.repository.Assistance_repo;
import com.sena.lunches.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssistanceServiceImpl implements AssistanceService{

    @Autowired
    private Assistance_repo assistance_repo;

    @Override
    public List<Assistance> getAssistance() {return assistance_repo.findAll();
    }

    @Override
    public Assistance saveAssistance (Assistance assistance) {return assistance_repo.save(assistance);
    }

    @Override
    public Assistance getAssistanceById(Integer id) {return assistance_repo.findById(id).orElse(null);
    }

    @Override
    public Assistance updateAssistance(Integer id, Assistance assistance) {
        Assistance oldAssistance = assistance_repo.findById(id).orElse(null);
        if (oldAssistance != null){
            oldAssistance.setId_authorization(assistance.getId_authorization());
            oldAssistance.setId_user(assistance.getId_user());
            oldAssistance.setTimes(assistance.getTimes());
            return assistance_repo.save(oldAssistance);
        }
        return null;
    }

    @Override
    public void deleteAssistance(Integer id) {
        assistance_repo.deleteById(id);
    }

}
