package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.repository.Archive_repo;
import com.sena.lunches.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private Archive_repo archive_repo;

    @Override
    public List<Archive> getArchive() {return archive_repo.findAll();
    }

    @Override
    public Archive saveArchive(Archive archive) {return archive_repo.save(archive);
    }

    @Override
    public Archive getArchiveById(Integer id) {return archive_repo.findById(id).orElse(null);
    }

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

    @Override
    public void deleteArchive(Integer id) {archive_repo.deleteById(id);
    }
}
