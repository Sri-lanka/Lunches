package com.sena.lunches.service;

import com.sena.lunches.entities.Program;

import java.util.List;

public interface ProgramService {

    public List<Program> getProgram();

    public Program saveProgram(Program program);

    public Program getProgramById(Integer id);

    public Program updateProgram(Integer id, Program program);

    public void deleteProgram(Integer id);
}
