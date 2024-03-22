package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Program;
import com.sena.lunches.repository.Program_repo;
import com.sena.lunches.repository.Program_sena_repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProgramServiceImpl {

    @Autowired
    private Program_sena_repo program_sena_repo;

    @Override
    public List<Program> getProgram() {return program_sena_repo.findAll();
    }

    @Override
    public Program saveProgram (Program file) {return program_sena_repo.save(file);
    }

    @Override
    public Program getProgramById(Integer id) {return program_sena_repo.findById(id).orElse(null);
    }

    @Override
    public Program updateProgram(Integer id, Program file) {
        Program oldProgram = program_sena_repo.findById(id).orElse(null);
        if (oldProgram != null){
            oldProgram.setName_program(file.getName_program());
            return program_sena_repo.save(oldProgram);
        }
        return null;
    }

    @Override
    public void deleteProgram(Integer id) {
        program_sena_repo.deleteById(id);
    }
}
