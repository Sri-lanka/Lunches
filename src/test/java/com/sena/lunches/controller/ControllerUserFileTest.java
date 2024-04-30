package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sena.lunches.entities.User_file;
import com.sena.lunches.entities.User_file;

import com.sena.lunches.service.UserFileService;
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

@WebMvcTest(controllers = ControllerUserFile.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerUserFileTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFileService userFileService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listUsers() throws Exception {
        User_file user_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        User_file user_file2=User_file.builder()
                .id_user_file(11)
                .id_file(12)
                .id_user(13)
                .asset(0)
                .build();
        List<User_file> user_fileList = Arrays.asList(user_file, user_file2);
        // Mock the service method to return the test data
        when(userFileService.getUser_file()).thenReturn(user_fileList);

        // Perform GET request to list users and verify the response
        ResultActions response = mockMvc.perform(get("/userFile/listUserFile"));
        // Verify the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view name
                .andExpect(model().attributeExists("User_File")) // Check if "user_file" attribute exists in the model
                .andExpect(model().attribute("User_File", user_fileList)); // Verify the content of "user_file" attribute


    }

    @Test
    void createNewUser() throws Exception{
        User_file newUser_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Simulation of saving User_file
        when(userFileService.saveUser_file(any())).thenReturn(newUser_file);
        //POST request to create a new User_file
        ResultActions response = mockMvc.perform(post("/userFile/newUserFile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser_file)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/userFile/listUserFile")); // Expected redirect URL

    }

    @Test
    void saveUserData() throws Exception{
        User_file newUser_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Simulation of saving User_file
        when(userFileService.saveUser_file(any())).thenReturn(newUser_file);
        //POST request to create a new User_file
        ResultActions response = mockMvc.perform(post("/userFile/newUserFile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser_file)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/userFile/listUserFile")); // Expected redirect URL

    }

    @Test
    void updateUser_file() throws Exception{
        User_file updatedUser_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Simulation of updating the User_file
        when(userFileService.updateUser_file(any(Integer.class), any(User_file.class))).thenReturn(updatedUser_file);
        // POST request to update an existing User_file and verify the response
        ResultActions response = mockMvc.perform(post("/userFile/editUserFile/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser_file)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/userFile/listUserFile")); // Expected redirect URL

    }

    @Test
    void updatingUser_file() throws Exception{
        User_file updatedUser_file=User_file.builder()
                .id_user_file(10)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Simulation of updating the User_file
        when(userFileService.updateUser_file(any(Integer.class), any(User_file.class))).thenReturn(updatedUser_file);
        // POST request to update an existing User_file and verify the response
        ResultActions response = mockMvc.perform(post("/userFile/editUserFile/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser_file)));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/userFile/listUserFile")); // Expected redirect URL

    }

    @Test
    void deleteUser_file() throws Exception{
        // ID of the user_file to delete
        int userFileIdToDelete = 1;
        User_file updatedUser_file=User_file.builder()
                .id_user_file(userFileIdToDelete)
                .id_file(11)
                .id_user(12)
                .asset(1)
                .build();
        // Simulation of the elimination of benefit
        doNothing().when(userFileService).deleteUser_file(anyInt());
        // Making the GET request to remove the benefit
        ResultActions response = mockMvc.perform(get("/userFile/delete/{id}", userFileIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));
        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/userFile/listUserFile")); // Expected redirect URL


    }
}