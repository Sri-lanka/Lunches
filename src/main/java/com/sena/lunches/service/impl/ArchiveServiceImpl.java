package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.entities.Message;
import com.sena.lunches.repository.Archive_repo;


import com.sena.lunches.repository.Message_repo;
import com.sena.lunches.service.ArchiveService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private Archive_repo archive_repo;


/*
    @Override
    public Archive updateArchive(Integer id, Archive archive) {Archive oldArchive = archive_repo.findById(id).orElse(null);
        if (oldArchive != null){
            oldArchive.setId_excuse(archive. getId_excuse());
            oldArchive.setId_message(archive.getId_message());
            oldArchive.setArchive_pdf(archive.getArchive_pdf());
            return archive_repo.save(oldArchive);
        }
        return null;
    }

    }*/

    @Override
    public List<Archive> getArchive() {return archive_repo.findAll();
    }

    @Override
    public Archive saveArchive(Archive archive) {return archive_repo.save(archive);
    }
/*
    @Override
    public Archive store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Archive archive = new Archive(fileName, file.getContentType(), file.getBytes());

        return archive_repo.save(archive);
    }*/


    @Override
    public Archive store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Archive archive = new Archive(fileName, file.getContentType(), file.getBytes());
        return archive_repo.save(archive);
    }

    @Override
    public Archive getArchiveById(String id) {return archive_repo.findById(id).orElse(null);
    }


    public Archive getFile(String id) {
        return archive_repo.findById(id).get();
    }

    public Stream<Archive> getAllFiles() {
        return archive_repo.findAll().stream();
    }

    @Override
    public void deleteArchive(String id) {archive_repo.deleteById(id);}

}
