package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sena.lunches.entities.Assistance;
import com.sena.lunches.entities.Benefit;
import com.sena.lunches.service.AssistanceService;
import com.sena.lunches.service.BenefitService;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = ControllerAssistance.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerAssistanceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AssistanceService assistanceService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void listAssistance() throws Exception {
        Assistance assistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();
        Assistance assistance2 = Assistance.builder()
                .id_assistance(17)
                .id_authorization(21)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();
        List<Assistance> assistanceList = Arrays.asList(assistance, assistance2);
        // Simulation of obtaining the list of assistance
        when(assistanceService.getAssistance()).thenReturn(assistanceList);
        // GET request to list assistance and verify response
        ResultActions response = mockMvc.perform(get("/assistance/listAssistance"));
        // Verification of the expected response
        response.andExpect(status().isOk())
                // Check the view
                .andExpect(view().name("admin/principal/list-users"))
                // Check the existence of the "benefits" attribute in the model
                .andExpect(model().attributeExists("assistance"))
                // Verify that the "benefits" attribute contains the list of benefits
                .andExpect(model().attribute("assistance", assistanceList));
    }

    @Test
    void addAssistance() throws Exception {
        // Data of the new assistance to be created
        Assistance newAssistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Simulation of saving assistance
        when(assistanceService.saveAssistance(any())).thenReturn(newAssistance);

        //POST request to create a new assistance
        ResultActions response = mockMvc.perform(post("/assistance/newAssistance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAssistance)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/assistance/listAssistance")); // Expected redirect URL
    }

    @Test
    void saveAssistance() throws Exception{
        // Data of the new assistance to be save
        Assistance newAssistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Simulation of saving assistance
        when(assistanceService.saveAssistance(any())).thenReturn(newAssistance);

        // POST request to save a new benefit and verify the response
        ResultActions response = mockMvc.perform(post("/assistance/newAssistance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAssistance)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after saving
                .andExpect(redirectedUrl("/assistance/listAssistance")); // Expected redirect URL
    }

    @Test
    void updateAssistance() throws Exception{
        // Assistance data to update
        Assistance updateAssistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();


        // Simulation of updating the assistance
        when(assistanceService.updateAssistance(any(Integer.class), any(Assistance.class))).thenReturn(updateAssistance);

        // POST request to update an existing benefit and verify the response
        ResultActions response = mockMvc.perform(post("/assistance/editAssistance/{id}", 16)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAssistance)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/assistance/listAssistance")); // Expected redirect URL
    }

    @Test
    void updatingAssistance() throws Exception{
        // Updated assistance data
        Assistance updateAssistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Mocking the updateBenefit method in the service
        when(assistanceService.updateAssistance(any(Integer.class), any(Assistance.class))).thenReturn(updateAssistance);

        // Performing the POST request to update the benefit
        ResultActions response = mockMvc.perform(post("/assistance/editAssistance/{id}", 16)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAssistance)));

        // Verifying the expected response
        response.andExpect(status().is3xxRedirection()) // Expecting redirection after update
                .andExpect(redirectedUrl("/assistance/listAssistance")); // Expected redirection URL
    }

    @Test
    void deleteAssistance() throws Exception{
        // ID of the assistance to delete
        int assistanceIdToDelete = 1;
        Assistance deleteAssistance = Assistance.builder()
                .id_assistance(16)
                .id_authorization(20)
                .date_time(LocalDateTime.of(2024, 6, 14,12,30))
                .build();

        // Simulation of the elimination of assistance
        doNothing().when(assistanceService).deleteAssistance(anyInt());

        // Making the GET request to remove the assistance
        ResultActions response = mockMvc.perform(get("/assistance/delete/{id}", assistanceIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect expected after deletion
                .andExpect(redirectedUrl("/assistance/listAssistance")); // Expected redirect URL
    }
}