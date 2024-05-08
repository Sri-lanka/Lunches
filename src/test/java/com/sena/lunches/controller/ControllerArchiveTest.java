package com.sena.lunches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sena.lunches.entities.Archive;
import com.sena.lunches.service.ArchiveService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        // Given
        Archive archive1 = new Archive();
        archive1.setId_archive(20);
        archive1.setTypeDoc("application/pdf");
        archive1.setName_archive("Identity card");
        archive1.setArchive_pdf("Mock PDF content".getBytes());

        Archive archive2 = new Archive();
        archive2.setId_archive(10);
        archive2.setTypeDoc("application/pdf");
        archive2.setName_archive("Identity card 2");
        archive2.setArchive_pdf("Mock PDF content 2".getBytes());

        List<Archive> listArchives = Arrays.asList(archive1, archive2);

        // Simulation of obtaining the list of archives
        when(archiveService.getArchive()).thenReturn(listArchives);

        // GET request to list benefits and verify response
        ResultActions response = mockMvc.perform(get("/archive/listArchive"));

        // Verification of the expected response
        response.andExpect(status().isOk())
                .andExpect(view().name("admin/principal/list-users")) // Check the view
                .andExpect(model().attributeExists("Archive")) // Check the existence of the "archive" attribute in the
                // model
                .andExpect(model().attribute("Archive", listArchives)); // Verify that the "archives" attribute contains
        // the list of archives
    }

    @Test
    void addArchive() throws Exception {

        // Creamos un archivo multipart
        MockMultipartFile file = new MockMultipartFile("file", "example.pdf", "application/pdf",
                "Mock PDF content".getBytes());

        // POST request to create a new archive
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/archive/newArchive")
                .file(file) // Agregamos el archivo como parte de la solicitud multipart
                .param("typeDoc", "application/pdf")
                .param("nameArchive", "Identity card"));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/archive/listArchive"));
    }

    @Test
    void saveArchive() throws Exception {
        // Creamos un archivo multipart
        MockMultipartFile file = new MockMultipartFile("file", "example.pdf", "application/pdf",
                "Mock PDF content".getBytes());

        // POST request to create a new archive
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/archive/newArchive")
                .file(file) // Agregamos el archivo como parte de la solicitud multipart
                .param("typeDoc", "application/pdf")
                .param("nameArchive", "Identity card"));

        // Verification of the expected response
        response.andExpect(status().is3xxRedirection()) // Redirect after creating
                .andExpect(redirectedUrl("/archive/listArchive"));
    }

    @Test
    void updateArchive() throws Exception {

        // Creamos un archivo multipart
        MockMultipartFile file = new MockMultipartFile("file", "example.pdf", "application/pdf",
                "Mock PDF content".getBytes());

        // Simulación de la actualización del archivo
        when(archiveService.updateArchive(any(Integer.class), any(MultipartFile.class))).thenReturn(null);

        // Solicitud POST para actualizar un archivo existente y verificar la respuesta
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/archive/editArchive/{id}", 1)
                .file(file) // Agregamos el archivo como parte de la solicitud multipart
                .param("name_archive", "New archive")
                .param("typeDoc", "application/pdf"));

        // Verificación de la respuesta esperada
        response.andExpect(status().is3xxRedirection()) // Redirección después de la actualización
                .andExpect(redirectedUrl("/archive/listArchive"));
    }

    @Test
    void updatingArchive() throws Exception {

        // Creamos un archivo multipart
        MockMultipartFile file = new MockMultipartFile("file", "example.pdf", "application/pdf",
                "Mock PDF content".getBytes());

        // Simulación de la actualización del archivo
        when(archiveService.updateArchive(any(Integer.class), any(MultipartFile.class))).thenReturn(null);

        // Solicitud POST para actualizar un archivo existente y verificar la respuesta
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/archive/editArchive/{id}", 1)
                .file(file) // Agregamos el archivo como parte de la solicitud multipart
                .param("name_archive", "New archive")
                .param("typeDoc", "application/pdf"));

        // Verificación de la respuesta esperada
        response.andExpect(status().is3xxRedirection()) // Redirección después de la actualización
                .andExpect(redirectedUrl("/archive/listArchive"));
    }

    @Test
    void getFile() {
        // Arrange
        Integer id = 1;
        byte[] fileContent = "Archivo PDF de prueba".getBytes();
        Archive archive = new Archive();
        archive.setId_archive(id);
        archive.setName_archive("example.pdf");
        archive.setArchive_pdf(fileContent);

        // Mock del servicio ArchiveService
        ArchiveService archiveService = mock(ArchiveService.class);
        when(archiveService.getArchiveById(id)).thenReturn(archive);

        // Construir el controlador con el servicio mock
        ControllerArchive archiveController = new ControllerArchive(archiveService);

        // Act
        ResponseEntity<byte[]> response = archiveController.getFile(id);

        // Assert
        // Verificar que se llama al método getArchiveById del servicio con el id
        // adecuado
        verify(archiveService).getArchiveById(id);

        // Verificar que la respuesta del controlador sea la esperada
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals("attachment; filename=\"example.pdf\"",
                        response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION)),
                () -> assertArrayEquals(fileContent, response.getBody()));

    }

    @Test
    void deleteArchive() throws Exception {
        // ID del archivo a eliminar
        int archiveIdToDelete = 1;
        System.out.println(archiveIdToDelete);
        // Simulación de la eliminación del archivo
        doNothing().when(archiveService).deleteArchive(anyInt());

        // Haciendo la solicitud GET para eliminar el archivo
        ResultActions response = mockMvc.perform(get("/archive/delete/{id}", archiveIdToDelete));

        // Verificación de la respuesta esperada
        response.andExpect(status().is3xxRedirection()) // Se espera redireccionamiento después de la eliminación
                .andExpect(redirectedUrl("/archive/listArchive"));
        System.out.println(response);
    }

}