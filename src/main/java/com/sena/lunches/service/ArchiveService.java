package com.sena.lunches.service;

import com.sena.lunches.entities.Archive;

import com.sena.lunches.repository.Archive_repo;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;


public interface ArchiveService {


    public List<Archive> getArchive();

    public Archive getArchiveById(Integer id);

    public Archive store( MultipartFile file) throws IOException ;


    public void deleteArchive(Integer id);
    public Archive updateArchive(int id, MultipartFile file) throws IOException;

}
