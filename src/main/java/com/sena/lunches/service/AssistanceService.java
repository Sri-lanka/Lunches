package com.sena.lunches.service;

import com.sena.lunches.entities.Assistance;
import java.util.List;

public interface AssistanceService {
    public List<Assistance> getAssistance();
    public List<Assistance> findByAssistance(int id_user);

    public Assistance saveAssistance(Assistance assistance);

    public Assistance getAssistanceById(Integer id);

    public Assistance updateAssistance(Integer id, Assistance assistance);

    public void deleteAssistance(Integer id);
}
