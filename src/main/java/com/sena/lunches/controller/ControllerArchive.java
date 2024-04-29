package com.sena.lunches.controller;

import com.sena.lunches.entities.Assistance;
import com.sena.lunches.entities.Message;
import com.sena.lunches.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;
import com.sena.lunches.entities.Archive;

/*import com.sena.lunches.message.ResponseFile;
import com.sena.lunches.message.ResponseMessage;*/
import com.sena.lunches.service.ArchiveService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/archive")
public class ControllerArchive {

    @Autowired
    private ArchiveService archiveService;

    /*
        @GetMapping("/listArchive")
        public String listUsers(Model model) {
            List<Archive> archiveData = archiveService.getArchive();
            model.addAttribute("Archive", archiveData);
            return "admin/principal/list-users";
        }

        @PostMapping("/newArchive")
        public String saveUserData (@ModelAttribute Archive archive) {
            archiveService.saveArchive(archive);
            return "redirect:/archive/listArchive";
        }

        @GetMapping("/editArchive/{idArchive}")
        public String updateArchive (@PathVariable Integer idArchive, Model model){
            model.addAttribute("archive", archiveService.getArchiveById(idArchive) );
            model.addAttribute("action","/archive/editArchive/" + idArchive);
            return "admin/principal/Forms/newArchive";
        }

        @PostMapping("/editArchive/{idArchive}")
        public String updatingArchive (@PathVariable Integer idArchive,@ModelAttribute Archive archive){
            archiveService.updateArchive(idArchive, archive);
            return "redirect:/archive/listArchive";
        }

        @GetMapping("/delete/{idArchive}")
        public String deleteArchive (@PathVariable Integer idArchive){
            archiveService.deleteArchive(idArchive);
            return "redirect:/archive/listArchive";
        }*/

    @GetMapping("/listArchive")
    public String listUsers(Model model) {
        List<Archive> listArchives = archiveService.getArchive();
        model.addAttribute("Archive", listArchives);
        return "admin/principal/list-users";
    }

    @GetMapping("/newArchive")
    public String createNewUser(Model model){
        model.addAttribute("archive", new Archive());
        model.addAttribute("action","");
        return "admin/principal/Forms/newArchive";
    }


/*    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            archiveService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
*/

    @PostMapping("/newArchive")
    public String saveUserData(@RequestParam("file") MultipartFile file) throws IOException {

        archiveService.store(file);
        return "redirect:/archive/listArchive";
    }


   /* @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = archiveService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/archive/files/")
                    .path(dbFile.getId_archive())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getId_message(),
                    dbFile.getName_archive(),
                    fileDownloadUri,
                    dbFile.getTypeDoc(),
                    dbFile.getArchive_pdf().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Archive archive = archiveService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archive.getName_archive() + "\"")
                .body(archive.getArchive_pdf());
    }

    @GetMapping("/delete/{idArchive}")
    public String deleteArchive (@PathVariable String idArchive){
        archiveService.deleteArchive(idArchive);
        return "redirect:/archive/listArchive";
    }
}