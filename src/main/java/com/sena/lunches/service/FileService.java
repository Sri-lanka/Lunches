package com.sena.lunches.service;


import com.sena.lunches.entities.File_sena;

import java.io.File;
import java.util.List;

public interface FileService {
    public List<File_sena> getFile_sena();

    public File_sena saveFile_sena(File_sena file_sena);

    public File_sena getFile_senaById(Integer id);

    public File_sena updateFile_sena(Integer id, File_sena file_sena);

    public void deleteFile_sena(Integer id);
}
