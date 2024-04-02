package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Program;
import com.sena.lunches.repository.Program_sena_repo;
import com.sena.lunches.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private Program_sena_repo program_sena_repo;

    @Override
    public List<Program> getProgram() {return program_sena_repo.findAll();
    }

    @Override
    public Program saveProgram (Program program) {return program_sena_repo.save(program);
    }

    @Override
    public Program getProgramById(Integer id) {return program_sena_repo.findById(id).orElse(null);
    }

    @Override
    public Program updateProgram(Integer id, Program program) {
        Program oldProgram = program_sena_repo.findById(id).orElse(null);
        if (oldProgram != null){
            oldProgram.setName_program(program.getName_program());
            return program_sena_repo.save(oldProgram);
        }
        return null;
    }

    @Override
    public void deleteProgram(Integer id) {
        program_sena_repo.deleteById(id);
    }
}
