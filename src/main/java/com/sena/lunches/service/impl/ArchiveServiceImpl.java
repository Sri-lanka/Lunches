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

//it is a Spring service component that provides functionality related to managing archive data.
// It  contains methods for creating, retrieving, updating, and deleting archive data.
@Service
public class ArchiveServiceImpl implements ArchiveService {

    // the ArchiveServiceImpl class gains access to the methods provided by Archive_repo,
    // allowing it to interact with the underlying data store  to perform CRUD operations or other data-related tasks involving Archive entities.
    @Autowired
    private Archive_repo archive_repo;

    // this method implementation retrieves all Archive entities from the underlying data store using the archive_repo instance and returns them as a List<Archive>.
    // It's a method  to provide functionality for fetching all archive data from the database and returning for possibly for display or further processing in the application.
    @Override
    public List<Archive> getArchive() {return archive_repo.findAll();
    }


    //takes a parameter of type MultipartFile named file. It returns an object of type Archive. It may throw an IOException
    @Override
    public Archive store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //This line retrieves the original filename from the MultipartFile object file.
        // It then cleans the path using StringUtils.cleanPath() method provided by the Spring Framework's StringUtils class.
        // This is to ensure that the filename is safe to use and doesn't contain any path traversal vulnerabilities.

        Archive archive = new Archive(fileName, file.getContentType(), file.getBytes());
        //This line creates a new Archive object using the filename, content type, and bytes of the file obtained from the MultipartFile object file.

        return archive_repo.save(archive);
        //This line saves the Archive object archive to the repository (archive_repo) using the save() method.  Finally, the saved Archive object is returned.

    }


    //takes a parameter of type MultipartFile named file. It returns an object of type Archive. It may throw an IOException
    @Override
    public Archive updateArchive(int id, MultipartFile file) throws IOException {
        // Retrieve the existing Archive object from the database
        Archive existingArchive = archive_repo.findById(id).orElse(null); //-> new ResourceNotFoundException("Archive not found"));
        if (existingArchive != null) {
            // Update the properties of the Archive object
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            existingArchive.setName_archive(fileName);
            existingArchive.setTypeDoc(file.getContentType());
            existingArchive.setArchive_pdf(file.getBytes());
            // Save the updated Archive object back to the database
            return archive_repo.save(existingArchive);
        }
        return null;
    }

    //it takes an Integer parameter id, representing the ID of the archive to retrieve from the database. It returns an Archive object.
    @Override
    public Archive getArchiveById(Integer id) {
        //This line retrieves the Archive object from the database based on the provided id using the findById() method of the repository (archive_repo).
        // The orElse(null) method is used to return null if no Archive object with the specified id is found.
        return archive_repo.findById(id).orElse(null);
    }


    //it takes an Integer parameter id, representing the ID of the archive to be deleted. It doesn't return any value(void)
    @Override
    public void deleteArchive(Integer id) {
        //This line calls the deleteById() method of the repository (archive_repo) to delete the archive from the database based on the provided id.
        archive_repo.deleteById(id);}

}
