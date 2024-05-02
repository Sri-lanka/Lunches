package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.repository.Archive_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ArchiveServiceImplTest {
    @Mock
    private Archive_repo archiveRepo;

    @InjectMocks
    private ArchiveServiceImpl archiveServiceImpl;
    @Test
    void getBenefit() {
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
        archive1.setId_archive(1);
        archive1.setTypeDoc("application/pdf");
        archive1.setName_archive("Identity card");
        archive1.setArchive_pdf(byteArrayPdf);

        Archive archive2 = new Archive();
        archive2.setId_archive(2);
        archive2.setTypeDoc("application/pdf");
        archive2.setName_archive("Identity card 2");
        archive2.setArchive_pdf(byteArrayPdf);

        List<Archive> mockArchive = Arrays.asList(archive1, archive2);

        // Configure repository mock behavior
        when(archiveRepo.findAll()).thenReturn(mockArchive);

        List<Archive> retrievedArchive = archiveServiceImpl.getArchive();

        // Then
        assertThat(retrievedArchive).isNotNull();
        assertThat(retrievedArchive).hasSize(2);
        assertThat(retrievedArchive).contains(archive1, archive2);

        // Verify repository method invocation
        verify(archiveRepo, times(1)).findAll();
    }

    @Test
    void store() throws IOException {
        // Arrange
        String fileName = "example.pdf";
        String contentType = "application/pdf";
        byte[] content = "Mock PDF content".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName, contentType, content);

        // Act
        archiveServiceImpl.store(mockMultipartFile);

        // Assert
        verify(archiveRepo).save(new Archive(fileName, contentType, content));

        /*
        // Simulate repository save failure
        doThrow(new RuntimeException("Failed to save archive")).when(archiveRepo).save(any());
        // Act & Assert
        //Call the store method and handle the expected exception
        assertThrows(RuntimeException.class, () -> archiveService.store(mockMultipartFile));
        */

    }


    @Test
    void updateArchive() throws IOException {
        // Arrange
        int id = 1;
        String fileName = "example.pdf";
        String contentType = "application/pdf";
        byte[] content = "Mock PDF content".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName, contentType, content);

        // Simulate repository findById failure
        when(archiveRepo.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertNull(archiveServiceImpl.updateArchive(id, mockMultipartFile));
    }

    @Test
    void getArchiveById() {
    }

    @Test
    void getFile() {
    }

    @Test
    void deleteArchive() {
    }
}