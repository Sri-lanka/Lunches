package com.sena.lunches.service.impl;

import com.sena.lunches.entities.File_sena;
import com.sena.lunches.repository.File_sena_repo;
import com.sena.lunches.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private File_sena_repo file_sena_repo;

    @Override
    public List<File_sena> getFile_sena() {return file_sena_repo.findAll();
    }

    @Override
    public File_sena saveFile_sena (File_sena file) {return file_sena_repo.save(file);
    }

    @Override
    public File_sena getFile_senaById(Integer id) {return file_sena_repo.findById(id).orElse(null);
    }

    @Override
    public File_sena updateFile_sena(Integer id, File_sena file) {
        File_sena oldFile_sena = file_sena_repo.findById(id).orElse(null);
        if (oldFile_sena != null){
            oldFile_sena.setN_file(file.getN_file());
            oldFile_sena.setId_program(file.getId_program());
            return file_sena_repo.save(oldFile_sena);
        }
        return null;
    }

    @Override
    public void deleteFile_sena(Integer id) {
        file_sena_repo.deleteById(id);
    }
}
