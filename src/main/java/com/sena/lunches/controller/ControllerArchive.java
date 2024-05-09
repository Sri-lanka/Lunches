package com.sena.lunches.controller;


import org.springframework.ui.Model;
import com.sena.lunches.entities.Archive;

import com.sena.lunches.service.ArchiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


//The @Controller annotation marks the ControllerArchive class as a Spring MVC controller, responsible for handling web requests.
//The @RequestMapping("/archive") annotation at the class level specifies that this controller will handle requests with URLs starting with "/archive".
@Controller
@RequestMapping("/archive")
public class ControllerArchive {

    //This line declares a private field named archiveService of type ArchiveService. This field will hold an instance of the ArchiveService class.
   @Autowired
    private ArchiveService archiveService;


    public ControllerArchive(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }


    @GetMapping("/listArchive")
    //This is the method signature. It returns a String representing the logical view name that will be rendered by the Spring MVC framework.
    public String listArchive(Model model) {
        //This line invokes the getArchive() method of the archiveService instance . It retrieves a list of archives, from  database.
        List<Archive> listArchives = archiveService.getArchive();
        //This line adds an attribute named "Archive" to the Model object. The "Archive" attribute will hold the list of archives retrieved from the archiveService.
        // This data can then be accessed in the view.
        model.addAttribute("Archive", listArchives);
        //This line specifies the logical view name to be returned. It tells Spring MVC to render the view named "admin/principal/list-users".
        return "admin/principal/list-users";
    }

    @GetMapping("/newArchive")
    public String addArchive(Model model){
        //This line adds a new attribute named "archive" to the Model object. It creates a new instance of the Archive class and adds it to the model.
        // This instance will be used to bind form data when submitting the form to add a new archive.
        model.addAttribute("archive", new Archive());
        //This line adds an attribute named "action" to the Model object with an empty string value.
        // This attribute can be used in the view to customize behavior based on the action being performed (in this case, adding a new archive).
        model.addAttribute("action","");
        // This line specifies the logical view name to be returned. It tells Spring MVC to render the view named "admin/principal/Forms/newArchive".
        return "admin/principal/Forms/newArchive";
    }


    @PostMapping("/newArchive")
    //This is the method signature. It takes a parameter named file annotated with @RequestParam.
    //This parameter represents the uploaded file. The MultipartFile class is a Spring representation of an uploaded file in a multipart request.
    //The method also declares that it may throw an IOException, which can occur during file operations.
    public String saveArchive(@RequestParam("file") MultipartFile file) throws IOException {
        //This line invokes the store() method of the archiveService instance, passing the file parameter. This method handles storing the uploaded file.
        archiveService.store(file);
        //This line redirects the client to the "/archive/listArchive" URL after the archive has been successfully saved.
        //This typically results in a GET request being made to the specified URL, effectively refreshing the page and displaying the updated list of archives.
        return "redirect:/archive/listArchive";
    }
    @GetMapping("/editArchive/{idArchive}")
    //It takes two parameters: idArchive, which is annotated with @PathVariable to extract the archive ID from the URL, and model, which is used to pass data to the view.
    //The method returns a String representing the logical view name that will be rendered by the Spring MVC framework.
    public String updateArchive (@PathVariable Integer idArchive, Model model){
        //This line adds an attribute named "archive" to the Model object.
        //It retrieves the archive object corresponding to the provided idArchive using the getArchiveById() method of the archiveService, and adds it to the model.
        //This object will be used to populate the form fields with the current data of the archive being edited.
        model.addAttribute("archive", archiveService.getArchiveById(idArchive) );
        //This line adds an attribute named "action" to the Model object. It specifies the action URL for the form submission.
        // Here, it sets the action to "/archive/editArchive/{idArchive}" with the specific idArchive in the URL path.
        // This URL will be used in the form submission to update the archive.
        model.addAttribute("action","/archive/editArchive/" + idArchive);
        //This line specifies the logical view name to be returned. It tells Spring MVC to render the view named "admin/principal/Forms/newArchive".
        return "admin/principal/Forms/newArchive";
    }

    @PostMapping("/editArchive/{idArchive}")
    //This is the method signature. It takes two parameters: idArchive, which is annotated with @PathVariable to extract the archive ID from the URL, and file,
    // which is annotated with @RequestParam to represent the uploaded file. The method also declares that it may throw an IOException, which can occur during file operations.
    public String updatingArchive (@PathVariable Integer idArchive, @RequestParam("file") MultipartFile file) throws IOException {
        //This line invokes the updateArchive() method of the archiveService instance, passing the idArchive and file parameters.
        // This method is responsible for updating the archive with the provided file. It returns the updated archive
        archiveService.updateArchive(idArchive, file);
        //This line redirects the client to the "/archive/listArchive" URL after the archive has been successfully updated.
        return "redirect:/archive/listArchive";
    }


    @GetMapping("/files/{id}")
    //This is the method signature. It takes a parameter named id annotated with @PathVariable to extract the archive ID from the URL.
    // The method returns a ResponseEntity<byte[]>, which represents the entire HTTP response, including headers and body containing the file contents as a byte array.
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        //This line retrieves the archive object corresponding to the provided id using the getArchiveById() method of the archiveService.
        Archive archive = archiveService.getArchiveById(id);

        //This line constructs and returns a ResponseEntity with the HTTP status code 200 (OK) indicating that the request was successful.
        // It sets the "Content-Disposition" header to "attachment" to indicate that the response body should be treated as an attachment,
        // and specifies the filename to be used when saving the attachment.
        // The body of the response contains the byte array representing the contents of the archive's PDF file, obtained from the getArchive_pdf() method of the archive object.
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archive.getName_archive() + "\"")
                .body(archive.getArchive_pdf());
        //In summary, this method retrieves the contents of a file associated with an archive, constructs an HTTP response with the file contents as a byte array,
        //and returns it to the client. This allows the client to download the file when accessing the specified URL.
    }

    @GetMapping("/delete/{idArchive}")
    public String deleteArchive (@PathVariable Integer idArchive){
        //This line invokes the deleteArchive() method of the archiveService instance, passing the idArchive parameter.
        //This method is responsible for deleting the archive with the specified ID from the data source (e.g., database).
        archiveService.deleteArchive(idArchive);
        //This line redirects the client to the "/archive/listArchive" URL after the archive has been successfully deleted.
        return "redirect:/archive/listArchive";
    }
}