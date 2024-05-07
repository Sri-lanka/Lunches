package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sena.lunches.entities.Archive;
import com.sena.lunches.entities.Benefit;
import com.sena.lunches.service.ArchiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = ControllerArchive.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerArchiveTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArchiveService archiveService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void listArchive() throws Exception {
        //function
        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive1 = new Archive();
        archive1.setId_archive(20);
        archive1.setTypeDoc("application/pdf");
        archive1.setName_archive("Identity card");
        archive1.setArchive_pdf(byteArrayPdf);

        Archive archive2 = new Archive();
        archive2.setId_archive(10);
        archive2.setTypeDoc("application/pdf");
        archive2.setName_archive("Identity card 2");
        archive2.setArchive_pdf(byteArrayPdf);

        List<Archive> listArchives = Arrays.asList(archive1, archive2);

        // Simulation of obtaining the list of archives
        when(archiveService.getArchive()).thenReturn(listArchives);

        // GET request to list benefits and verify response
        ResultActions response = mockMvc.perform(get("/archive/listArchive"));

        // Verification of the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view
                .andExpect(model().attributeExists("Archive")) // Check the existence of the "archive" attribute in the model
                .andExpect(model().attribute("Archive", listArchives)); // Verify that the "archives" attribute contains the list of archives
    }

    @Test
    void addArchive() throws Exception {
        //function
        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(20);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf(byteArrayPdf);

        // Simulation of saving benefit
        when(archiveService.store(any())).thenReturn(archive);

        //POST request to create a new benefit
        ResultActions response;
        response = mockMvc.perform(post("/archive/newArchive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(archive)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/archive/listArchive"));
    }

    @Test
    void saveArchive() {
    }

    @Test
    void updateArchive() {
    }

    @Test
    void updatingArchive() {
    }

    @Test
    void getFile() {
    }

    @Test
    void deleteArchive() {
    }
}