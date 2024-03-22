package com.sena.lunches.service;

import com.sena.lunches.entities.Program;

import java.util.List;

public interface ProgramService {

    public List<Program> getProgram();

    public Program saveProgram_sena(Program program);

    public Program getProgram_senaById(Integer id);

    public Program updateProgram_sena(Integer id, Program program);

    public void deleteProgram(Integer id);
}
