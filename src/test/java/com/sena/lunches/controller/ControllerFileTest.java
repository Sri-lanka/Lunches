package com.sena.lunches.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.sena.lunches.entities.File_sena;
import com.sena.lunches.service.FileService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ControllerFile.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerFileTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FileService fileService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listUsers() throws Exception {
        // Prepare test data
        File_sena file_sena1 = File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        File_sena file_sena2 = File_sena.builder()
                .id_file(11)
                .n_file(2)
                .id_program(6)
                .build();
        List<File_sena> file_senaList = Arrays.asList(file_sena1, file_sena2);

        // Mock the service method to return the test data
        when(fileService.getFile_sena()).thenReturn(file_senaList);

        // Perform GET request to list users and verify the response
        ResultActions response = mockMvc.perform(get("/file/listFileSena"));

        // Verify the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view name
                .andExpect(model().attributeExists("file")) // Check if "file" attribute exists in the model
                .andExpect(model().attribute("file", file_senaList)); // Verify the content of "file" attribute

    }

    @Test
    void createNewUser() throws Exception {
        File_sena newFile_sena = File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // Simulation of saving File
        when(fileService.saveFile_sena(any())).thenReturn(newFile_sena);

        //POST request to create a new File
        ResultActions response = mockMvc.perform(post("/file/newFileSena")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newFile_sena)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/file/listFileSena")); // Expected redirect URL

    }

    @Test
    void saveUserData() throws Exception  {
        File_sena newFile_sena = File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // Simulation of saving File
        when(fileService.saveFile_sena(any())).thenReturn(newFile_sena);

        //POST request to create a new File
        ResultActions response = mockMvc.perform(post("/file/newFileSena")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newFile_sena)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/file/listFileSena")); // Expected redirect URL

    }

    @Test
    void updateFile_sena() throws Exception {
        File_sena updatedFile_sena = File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // Simulation of updating the File
        when(fileService.updateFile_sena(any(Integer.class), any(File_sena.class))).thenReturn(updatedFile_sena);
        // POST request to update an existing File and verify the response
        ResultActions response = mockMvc.perform(post("/file/editFileSena/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFile_sena)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/file/listFileSena")); // Expected redirect URL

    }

    @Test
    void updatingFile_sena() throws Exception {
        File_sena updatedFile_sena = File_sena.builder()
                .id_file(10)
                .n_file(1)
                .id_program(4)
                .build();
        // Mocking the updatedFile_sena method in the service
        when(fileService.updateFile_sena(any(Integer.class), any(File_sena.class))).thenReturn(updatedFile_sena);
        // Performing the POST request to update the File
        ResultActions response = mockMvc.perform(post("/file/editFileSena/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFile_sena)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/file/listFileSena")); // Expected redirect URL

    }

    @Test
    void deleteFile_sena() throws Exception {
        // ID of the file to delete
        int fileIdToDelete = 1;
        File_sena deleteFile_sena = File_sena.builder()
                .id_file(fileIdToDelete)
                .n_file(1)
                .id_program(4)
                .build();
        // Simulation of the elimination of benefit
        doNothing().when(fileService).deleteFile_sena(anyInt());
        // Making the GET request to remove the benefit
        ResultActions response = mockMvc.perform(get("/file/delete/{id}", fileIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/file/listFileSena")); // Expected redirect URL

    }


}