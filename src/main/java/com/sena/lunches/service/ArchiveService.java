package com.sena.lunches.service;

import com.sena.lunches.entities.Archive;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


public interface ArchiveService {

    // returns a List of Archive objects. This method retrieves a collection of Archive objects,
    // from  database and returns them for further processing or display.
    public List<Archive> getArchive();

    //It takes an Integer parameter id and returns an Archive object corresponding to that id.
    public Archive getArchiveById(Integer id);

    //It takes a MultipartFile parameter file, representing a file uploaded via a form.
    // The method is to handle the content of the file, storing it in a database , and then return an Archive object,
    // which could represent metadata or information about the stored file. Additionally, it declares that it throw an IOException,
    // indicating that it's dealing with input/output operations that could encounter errors.
    public Archive store( MultipartFile file) throws IOException ;

    //It takes an Integer parameter id, representing the identifier of the Archive object to be deleted.
    public void deleteArchive(Integer id);

    //It takes an int parameter id representing the identifier of the Archive object to be updated and a MultipartFile parameter file representing the updated file content.
    // Additionally, it declares that it might throw an IOException, indicating that it's dealing with input/output operations that could encounter errors.
    public Archive updateArchive(int id, MultipartFile file) throws IOException;

}
