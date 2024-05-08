package com.sena.lunches.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.sena.lunches.entities.Message;

import com.sena.lunches.service.MessageService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ControllerMessage.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerMessageTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listMessage() throws Exception {
        // Prepare test data
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();

        Message message2 = Message.builder()
                .id_message(15)
                .id_user(2)
                .id_archive(3)
                .typeMessage(2)
                .description_message("A Excuse")
                .date_send(LocalDate.of(2024, 12, 13))
                .build();

        List<Message> listMessage = Arrays.asList(message, message2);

        // Mock the service method to return the test data
        when(messageService.getMessage()).thenReturn(listMessage);

        // Perform GET request to list users and verify the response
        ResultActions response = mockMvc.perform(get("/message/listMessage"));

        // Verify the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view name
                .andExpect(model().attributeExists("message")) // Check if "file" attribute exists in the model
                .andExpect(model().attribute("message", listMessage)); // Verify the content of "file" attribute

    }

    @Test
    void addMessage() throws Exception {
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // Simulation of saving File
        when(messageService.saveMessage(any())).thenReturn(message);

        //POST request to create a new File
        ResultActions response = mockMvc.perform(post("/message/newMessage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/message/listMessage")); // Expected redirect URL

    }

   @Test
    void saveMessage() throws Exception  {
       Message message = Message.builder()
               .id_message(16)
               .id_user(1)
               .id_archive(2)
               .typeMessage(1)
               .description_message("A report")
               .date_send(LocalDate.of(2024, 12, 14))
               .build();
        // Simulation of saving File
        when(messageService.saveMessage(any())).thenReturn(message);

        //POST request to create a new File
        ResultActions response = mockMvc.perform(post("/message/newMessage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/message/listMessage")); // Expected redirect URL

    }

    @Test
    void updateMessage() throws Exception {
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // Simulation of updating the File
        when(messageService.updateMessage(any(Integer.class), any(Message.class))).thenReturn(message);
        // POST request to update an existing File and verify the response
        ResultActions response = mockMvc.perform(post("/message/editMessage/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/message/listMessage")); // Expected redirect URL

    }

    @Test
    void updatingFileSena() throws Exception {
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // Simulation of updating the File
        when(messageService.updateMessage(any(Integer.class), any(Message.class))).thenReturn(message);
        // POST request to update an existing File and verify the response
        ResultActions response = mockMvc.perform(post("/message/editMessage/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/message/listMessage")); // Expected redirect URL

    }

    @Test
    void deleteFileSena() throws Exception {
        // ID of the file to delete
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // Simulation of the elimination of benefit
        doNothing().when(messageService).deleteMessage(anyInt());
        // Making the GET request to remove the benefit
        ResultActions response = mockMvc.perform(get("/message/delete/{id}", 16)
                .contentType(MediaType.APPLICATION_JSON));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/message/listMessage")); // Expected redirect URL

    }


}