package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.repository.Archive_repo;
import com.sena.lunches.service.ArchiveService;


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


    @Override
    public List<Archive> getArchive() {return archive_repo.findAll();
    }



    @Override
    public Archive store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Archive archive = new Archive(fileName, file.getContentType(), file.getBytes());

        return archive_repo.save(archive);
    }


    @Override
    public Archive updateArchive(int id, MultipartFile file) throws IOException {
        // Step 1: Retrieve the existing Archive object from the database
        Archive existingArchive = archive_repo.findById(id).orElse(null); //-> new ResourceNotFoundException("Archive not found"));
        if (existingArchive != null) {
            // Step 2: Update the properties of the Archive object
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            existingArchive.setName_archive(fileName);
            existingArchive.setTypeDoc(file.getContentType());
            existingArchive.setArchive_pdf(file.getBytes());
            // Step 3: Save the updated Archive object back to the database
            return archive_repo.save(existingArchive);
        }
        return null;
    }

    @Override
    public Archive getArchiveById(Integer id) {return archive_repo.findById(id).orElse(null);
    }

    @Override
    public Archive getFile(Integer id) {
        return archive_repo.findById(id).get();
    }


    @Override
    public void deleteArchive(Integer id) {archive_repo.deleteById(id);}

}
