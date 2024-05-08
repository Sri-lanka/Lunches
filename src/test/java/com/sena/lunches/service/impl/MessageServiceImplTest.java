package com.sena.lunches.service.impl;

import com.sena.lunches.entities.Message;
import com.sena.lunches.repository.Message_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private Message_repo message_repo;

    @InjectMocks
    private MessageServiceImpl messsageServiceImpl;


    @Test
    void getMessage() {
        // Given
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();

        Message message2 = Message.builder()
                .id_message(10)
                .id_user(2)
                .id_archive(1)
                .typeMessage(2)
                .description_message("A excuse")
                .date_send(LocalDate.of(2024, 12, 15))
                .build();


        List<Message> mockBenefits = Arrays.asList(message, message2);

        // Configure repository mock behavior
        when(message_repo.findAll()).thenReturn(mockBenefits);

        // When
        List<Message> retrievedMessage = messsageServiceImpl.getMessage();

        // Then
        assertThat(retrievedMessage).isNotNull();
        assertThat(retrievedMessage).hasSize(2);
        assertThat(retrievedMessage).contains(message, message2);

        // Verify repository method invocation
        verify(message_repo, times(1)).findAll();
    }
    @Test
    void saveMessage() {
        // Given
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // Configure repository mock behavior
        when(message_repo.save(Mockito.any(Message.class))).thenReturn(message);

        // When
        Message savedMessage = messsageServiceImpl.saveMessage(message);

        // Then
        assertThat(savedMessage).isNotNull();
        assertThat(savedMessage.getId_message()).isEqualTo(16);
        assertThat(savedMessage.getId_user()).isEqualTo(1);
        assertThat(savedMessage.getId_archive()).isEqualTo(2);
        assertThat(savedMessage.getTypeMessage()).isEqualTo(1);
        assertThat(savedMessage.getDescription_message()).isEqualTo("A report");
        assertThat(savedMessage.getDate_send()).isEqualTo(LocalDate.of(2024, 12, 14));

        // Verify repository method invocation
        verify(message_repo, times(1)).save(any(Message.class));
    }
   @Test
    void getMessageById() {
       // Given
       Message message = Message.builder()
               .id_message(16)
               .id_user(1)
               .id_archive(2)
               .typeMessage(1)
               .description_message("A report")
               .date_send(LocalDate.of(2024, 12, 14))
               .build();

        // Configure repository mock behavior
        when(message_repo.findById(16)).thenReturn(Optional.of(message));

        // When
        Message retrievedMessage = messsageServiceImpl.getMessageById(16);

        // Then
        assertThat(retrievedMessage).isNotNull();
        assertThat(retrievedMessage.getId_message()).isEqualTo(16);
        assertThat(retrievedMessage.getDescription_message()).isEqualTo("A report");

        // Verify repository method invocation
        verify(message_repo, times(1)).findById(16);
    }
    @Test
    void updateMessage() {
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();

        when(message_repo.findById(16)).thenReturn(Optional.of(message));
        when(message_repo.save(Mockito.any(Message.class))).thenReturn(message);

        // When
        Message updateMessage = messsageServiceImpl.updateMessage(16, message);

        // Then
        assertThat(updateMessage).isNotNull();

    }

    @Test
    public void deleteMessage() {
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();

        messsageServiceImpl.deleteMessage(16);

        // Verificar que se haya llamado al método delete del repositorio con el beneficio correcto
        verify(message_repo, times(1)).deleteById(16);
    }


}