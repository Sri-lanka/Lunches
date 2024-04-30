package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sena.lunches.entities.Program;
import com.sena.lunches.service.ProgramService;
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

@WebMvcTest(controllers = ControllerProgram.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerProgramTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProgramService programService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listUsers() throws Exception {
        Program program=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        Program program2=Program.builder()
                .id_program(12)
                .name_program("english")
                .build();
        List<Program> programList = Arrays.asList(program, program2);
        // Mock the service method to return the test data
        when(programService.getProgram()).thenReturn(programList);

        // Perform GET request to list users and verify the response
        ResultActions response = mockMvc.perform(get("/program/listProgram"));
        // Verify the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view name
                .andExpect(model().attributeExists("program")) // Check if "program" attribute exists in the model
                .andExpect(model().attribute("program", programList)); // Verify the content of "program" attribute

    }

    @Test
    void createNewUser() throws Exception {
        Program newProgram=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // Simulation of saving Program
        when(programService.saveProgram(any())).thenReturn(newProgram);

        //POST request to create a new Program
        ResultActions response = mockMvc.perform(post("/program/newProgram")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProgram)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/program/listProgram")); // Expected redirect URL
        
    }

    @Test
    void saveUserData() throws Exception {
        Program newProgram=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // Simulation of saving Program
        when(programService.saveProgram(any())).thenReturn(newProgram);

        //POST request to create a new Program
        ResultActions response = mockMvc.perform(post("/program/newProgram")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProgram)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/program/listProgram")); // Expected redirect URL


    }

    @Test
    void updateProgram() throws Exception {
        Program updatedProgram=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // Simulation of updating the Program
        when(programService.updateProgram(any(Integer.class), any(Program.class))).thenReturn(updatedProgram);
        // POST request to update an existing Program and verify the response
        ResultActions response = mockMvc.perform(post("/program/editProgram/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProgram)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/program/listProgram")); // Expected redirect URL


    }

    @Test
    void updatingProgram() throws Exception {
        Program updatedProgram=Program.builder()
                .id_program(10)
                .name_program("adso")
                .build();
        // Simulation of updating the Program
        when(programService.updateProgram(any(Integer.class), any(Program.class))).thenReturn(updatedProgram);
        // POST request to update an existing Program and verify the response
        ResultActions response = mockMvc.perform(post("/program/editProgram/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProgram)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/program/listProgram")); // Expected redirect URL

    }

    @Test
    void deleteProgram() throws Exception {
        // ID of the program to delete
        int programIdToDelete = 1;
        Program deleteProgram=Program.builder()
                .id_program(programIdToDelete)
                .name_program("adso")
                .build();
        // Simulation of the elimination of Program
        doNothing().when(programService).deleteProgram(anyInt());
        // Making the GET request to remove the Program
        ResultActions response = mockMvc.perform(get("/program/delete/{id}", programIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/program/listProgram")); // Expected redirect URL



    }


}