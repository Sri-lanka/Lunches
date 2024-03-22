package com.sena.lunches.service;

import com.sena.lunches.entities.Archive;


import java.util.List;

public interface ArchiveService {
    public List<Archive> getArchive();

    public Archive saveArchive(Archive archive);

    public Archive getArchiveById(Integer id);

    public Archive updateArchive(Integer id, Archive archive);

    public void deleteArchive(Integer id);
    
    
}
