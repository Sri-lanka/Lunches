package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sena.lunches.entities.Authorization;
import com.sena.lunches.service.AuthorizationService;
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

import java.time.LocalDate;
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

@WebMvcTest(controllers = ControllerAuthorization.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorizationService authorizationService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void listAuthorization() throws Exception{

        Authorization authorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        Authorization authorization2 = Authorization.builder()
                .id_authorization(17)
                .id_user(21)
                .id_user_autho(16)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        List<Authorization> authorizationList = Arrays.asList(authorization, authorization2);

        // Simulation of obtaining the list of benefits
        when(authorizationService.getAuthorization()).thenReturn(authorizationList);

        // GET request to list benefits and verify response
        ResultActions response = mockMvc.perform(get("/authorization/listAuthorization"));

        // Verification of the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view
                .andExpect(model().attributeExists("authorization")) // Check the existence of the "authorization" attribute in the model
                .andExpect(model().attribute("authorization", authorizationList)); // Verify that the "authorization" attribute contains the list of authorization

    }

    @Test
    void addAuthorization() throws Exception{
        // Data of the new authorization to be created
        Authorization newAuthorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Simulation of saving authorization
        when(authorizationService.saveAuthorization(any())).thenReturn(newAuthorization);

        //POST request to create a new authorization
        ResultActions response = mockMvc.perform(post("/authorization/newAuthorization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAuthorization)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/authorization/listAuthorization")); // Expected redirect URL
    }

    @Test
    void saveAuthorization() throws Exception{
        // Data of the new authorization to be save
        Authorization newAuthorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Simulation of saving authorization
        when(authorizationService.saveAuthorization(any())).thenReturn(newAuthorization);

        // POST request to save a new authorization and verify the response
        ResultActions response = mockMvc.perform(post("/authorization/newAuthorization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAuthorization)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after saving
                .andExpect(redirectedUrl("/authorization/listAuthorization")); // Expected redirect URL
    }

    @Test
    void updateAuthorization() throws Exception{
        // authorization data to update
        Authorization updateAuthorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Simulation of updating the authorization
        when(authorizationService.updateAuthorization(any(Integer.class), any(Authorization.class))).thenReturn(updateAuthorization);

        // POST request to update an existing authorization and verify the response
        ResultActions response = mockMvc.perform(post("/authorization/editAuthorization/{id}", 16)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAuthorization)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/authorization/listAuthorization")); // Expected redirect URL
    }

    @Test
    void updatingAuthorization() throws Exception{
        // Updated authorization data
        Authorization updateAuthorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Mocking the updateAuthorization method in the service
        when(authorizationService.updateAuthorization(any(Integer.class), any(Authorization.class))).thenReturn(updateAuthorization);

        // Performing the POST request to update the authorization
        ResultActions response = mockMvc.perform(post("/authorization/editAuthorization/{id}", 16)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAuthorization)));

        // Verifying the expected response
        response.andExpect(status().is3xxRedirection()) // Expecting redirection after update
                .andExpect(redirectedUrl("/authorization/listAuthorization")); // Expected redirection URL
    }

    @Test
    void deleteAuthorization() throws Exception{
        // ID of the authorization to delete
        int authorizationIdToDelete = 1;
        Authorization deletedAuthorization = Authorization.builder()
                .id_authorization(16)
                .id_user(20)
                .id_user_autho(15)
                .description_excuse("description")
                .date_apli(LocalDate.of(2024, 5, 23))
                .build();

        // Simulation of the elimination of authorization
        doNothing().when(authorizationService).deleteAuthorization(anyInt());

        // Making the GET request to remove the authorization
        ResultActions response = mockMvc.perform(get("/authorization/delete/{id}", authorizationIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect expected after deletion
                .andExpect(redirectedUrl("/authorization/listAuthorization")); // Expected redirect URL
    }
}