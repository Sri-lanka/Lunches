package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sena.lunches.entities.User_sena;

import com.sena.lunches.service.UserSenaService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(controllers = ControllerUser.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerUserTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserSenaService userSenaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listUsers() throws Exception{
        User_sena user_sena = User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        User_sena user_sena2 = User_sena.builder()
                .id_user(12)
                .document(12)
                .type_document(2)
                .rol(1)
                .name_1("bl")
                .name_2("ack")
                .last_name_1("df")
                .last_name_2("as")
                .age(17)
                .Email("sag@gmail.com")
                .telephone(3136549872L)
                .keyword("4321")
                .state(0)
                .id_benefit(14)
                .build();
        List<User_sena> user_senaList = Arrays.asList(user_sena, user_sena2);

        // Mock the service method to return the test data
        when(userSenaService.getUser_sena()).thenReturn(user_senaList);
        // Perform GET request to list users and verify the response
        ResultActions response = mockMvc.perform(get("/"));

        // Verify the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", user_senaList));

    }

    @Test
    void addUser() throws Exception{
        User_sena newUser_sena = User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com") // Corregido: Cambiado Email a email
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();

        // Simulation of saving User_sena
        when(userSenaService.saveUser_sena(any())).thenReturn(newUser_sena);

        //POST request to create a new User_sena
        ResultActions response = mockMvc.perform(post("/newUser") // Corregido: Cambiado "//newUser" a "/newUser"
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser_sena)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirección después de crear
                .andExpect(redirectedUrl("/")); // URL de redirección esperada

    }

    @Test
    void saveUserData() throws Exception{
        User_sena newUser_sena = User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();

        // Simulation of saving User_sena
        when(userSenaService.saveUser_sena(any())).thenReturn(newUser_sena);

        //POST request to create a new User_sena
        ResultActions response = mockMvc.perform(post("/newUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser_sena)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/")); // Expected redirect URL

    }

    @Test
    void updateUser_sena() throws Exception{
        User_sena updatedUser_sena = User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        // Simulation of updating the User_sena
        when(userSenaService.updateUser_sena(any(Integer.class), any(User_sena.class))).thenReturn(updatedUser_sena);
        // POST request to update an existing User_sena and verify the response
        ResultActions response = mockMvc.perform(post("/editUser/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser_sena)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/")); // Expected redirect URL

    }

    @Test
    void updatingUser_sena() throws Exception{
        User_sena updatedUser_sena = User_sena.builder()
                .id_user(11)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        // Simulation of updating the User_sena
        when(userSenaService.updateUser_sena(any(Integer.class), any(User_sena.class))).thenReturn(updatedUser_sena);
        // POST request to update an existing User_sena and verify the response
        ResultActions response = mockMvc.perform(post("/editUser/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser_sena)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/")); // Expected redirect URL

    }

    @Test
    void deleteUser_sena() throws Exception{
        // ID of the user_file to delete
        int userSenaIdToDelete = 1;
        User_sena newUser_sena = User_sena.builder()
                .id_user(userSenaIdToDelete)
                .document(11)
                .type_document(1)
                .rol(1)
                .name_1("blue")
                .name_2("black")
                .last_name_1("asdf")
                .last_name_2("fdas")
                .age(18)
                .Email("asdfsag@gmail.com")
                .telephone(3216549872L)
                .keyword("1234")
                .state(1)
                .id_benefit(11)
                .build();
        // Simulation of the elimination of benefit
        doNothing().when(userSenaService).deleteUser_sena(anyInt());
        // Making the GET request to remove the benefit
        ResultActions response = mockMvc.perform(get("/delete/{id}", userSenaIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/")); // Expected redirect URL

    }
}