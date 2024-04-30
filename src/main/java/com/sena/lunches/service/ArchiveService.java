package com.sena.lunches.service;

import com.sena.lunches.entities.Archive;

import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public interface ArchiveService {
/*

    public Archive saveArchive(Archive archive);
*/

    public List<Archive> getArchive();

    public Archive getArchiveById(Integer id);

    public Archive saveArchive(Archive archive);

    public Archive store( MultipartFile file) throws IOException ;

    public Archive getFile(Integer id);

    public Stream<Archive> getAllFiles();

    public void deleteArchive(Integer id);
    public Archive updateArchive(int id, MultipartFile file) throws IOException;

}
