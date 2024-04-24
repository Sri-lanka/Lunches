package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sena.lunches.entities.Benefit;
import com.sena.lunches.service.BenefitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ControllerBenefit.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ControllerBenefitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BenefitService benefitService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void listUsers() throws Exception {
        // Datos de beneficios simulados
        Benefit benefit1 = Benefit.builder()
                .id_benefit(1)
                .nom_benefit("New benefit 1")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();
        Benefit benefit2 = Benefit.builder()
                .id_benefit(2)
                .nom_benefit("New benefit 2")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();

        List<Benefit> benefitList = Arrays.asList(benefit1, benefit2);

        // Simulation of obtaining the list of benefits
        when(benefitService.getBenefit()).thenReturn(benefitList);

        // GET request to list benefits and verify response
        ResultActions response = mockMvc.perform(get("/benefit/listBenefit"));

        // Verification of the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view
                .andExpect(model().attributeExists("benefits")) // Check the existence of the "benefits" attribute in the model
                .andExpect(model().attribute("benefits", benefitList)); // Verify that the "benefits" attribute contains the list of benefits

    }

    @Test
    void createNewUser() throws Exception {
        // Data of the new benefit to be created
        Benefit newBenefit = Benefit.builder()
                .id_benefit(1)
                .nom_benefit("New benefit ")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();

        // Simulation of saving benefit
        when(benefitService.saveBenefit(any())).thenReturn(newBenefit);

        //POST request to create a new benefit
        ResultActions response = mockMvc.perform(post("/benefit/newBenefits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBenefit)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/benefit/listBenefit")); // Expected redirect URL
    }

    @Test
    void saveUserData() throws Exception {
        // Data of the new benefit to be save
        Benefit newBenefit = Benefit.builder()
                .id_benefit(1)
                .nom_benefit("New benefit ")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();

        // Simulation of saving benefit
        when(benefitService.saveBenefit(any())).thenReturn(newBenefit);

        // POST request to save a new benefit and verify the response
        ResultActions response = mockMvc.perform(post("/benefit/newBenefits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBenefit)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after saving
                .andExpect(redirectedUrl("/benefit/listBenefit")); // Expected redirect URL
    }


    @Test
    void updateBenefit() throws Exception {
        // Benefit data to update
        Benefit updatedBenefit = Benefit.builder()
                .id_benefit(1)
                .nom_benefit("New benefit")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();

        // Simulation of updating the benefit
        when(benefitService.updateBenefit(any(Integer.class), any(Benefit.class))).thenReturn(updatedBenefit);

        // POST request to update an existing benefit and verify the response
        ResultActions response = mockMvc.perform(post("/benefit/editBenefit/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBenefit)));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after update
                .andExpect(redirectedUrl("/benefit/listBenefit")); // Expected redirect URL
    }

    @Test
    void updatingBenefit() throws Exception {
        // Updated benefit data
        Benefit updatedBenefit = Benefit.builder()
                .id_benefit(1)
                .nom_benefit("New benefit")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();

        // Mocking the updateBenefit method in the service
        when(benefitService.updateBenefit(any(Integer.class), any(Benefit.class))).thenReturn(updatedBenefit);

        // Performing the POST request to update the benefit
        ResultActions response = mockMvc.perform(post("/benefit/editBenefit/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBenefit)));

        // Verifying the expected response
        response.andExpect(status().is3xxRedirection()) // Expecting redirection after update
                .andExpect(redirectedUrl("/benefit/listBenefit")); // Expected redirection URL
    }

    @Test
    void deleteBenefit() throws Exception {
        // ID of the benefit to delete
        int benefitIdToDelete = 1;
        Benefit delatedBenefit = Benefit.builder()
                .id_benefit(benefitIdToDelete)
                .nom_benefit("New benefit")
                .description_benefit("Description")
                .date_start(LocalDate.now())
                .date_end(LocalDate.now().plusMonths(1))
                .build();

        // Simulation of the elimination of benefit
        doNothing().when(benefitService).deleteBenefit(anyInt());

        // Making the GET request to remove the benefit
        ResultActions response = mockMvc.perform(get("/benefit/delete/{id}", benefitIdToDelete)
                .contentType(MediaType.APPLICATION_JSON));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect expected after deletion
                .andExpect(redirectedUrl("/benefit/listBenefit")); // Expected redirect URL
    }



}