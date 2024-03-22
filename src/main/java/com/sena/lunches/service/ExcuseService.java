package com.sena.lunches.service;

import com.sena.lunches.entities.Benefit;
import com.sena.lunches.entities.Excuse;

import java.util.List;

public interface ExcuseService {
    public List<Excuse> getExcuse();

    public Excuse saveExcuse(Excuse excuse);

    public Excuse getExcuseById(Integer id);

    public Excuse updateExcuse(Integer id, Excuse excuse);

    public void deleteExcuse(Integer id);
}
