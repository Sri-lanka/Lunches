package com.sena.lunches.repository;

import com.sena.lunches.entities.Archive;
import com.sena.lunches.entities.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)
public class MessageRepoTest {
    @Autowired
    private Message_repo message_repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveMessage() {
        // Given
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // When
        Message savedmessage = entityManager.persist(message);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedmessage.getId_message()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the benefit can be retrieved from the repository
        Optional<Message> retrievedMessage = message_repo.findById(savedmessage.getId_message());
        assertThat(retrievedMessage).isPresent(); // Check if benefit is found
        assertThat(retrievedMessage.get().getDescription_message()).isEqualTo("A report"); // Verify attribute value
    }

   @Test
    public void testGetMessage(){
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
               .id_message(15)
               .id_user(2)
               .id_archive(3)
               .typeMessage(2)
               .description_message("A Excuse")
               .date_send(LocalDate.of(2024, 12, 13))
               .build();

        message_repo.save(message);
        message_repo.save(message2);

        List<Message> messageList = message_repo.findAll();

        assertThat(messageList).isNotNull();
        assertThat(messageList.size()).isEqualTo(2);
    }


    @Test
    public void testFindMessage() {
        // Given
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // When
        Message savedMessage = message_repo.save(message);

        // Then
        assertThat(savedMessage.getId_message()).isNotNull(); // Verifica que el ID se haya asignado correctamente

        Optional<Message> retrievedMessageOptional = message_repo.findById(savedMessage.getId_message());

        assertThat(retrievedMessageOptional).isPresent();
        Message retrievedMessage = retrievedMessageOptional.get();
        assertThat(retrievedMessage.getDescription_message()).isEqualTo("A report"); // Verifica el nombre del beneficio


    }

    @Test
    public void testUpdateMessage() {
        // Given
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();
        // When
        Message savedMessage = message_repo.save(message);

        // Retrieve the saved benefit from the repository
        Optional<Message> optionalBenefit = message_repo.findById(savedMessage.getId_message());
        assertThat(optionalBenefit).isPresent(); // Verify that the benefit is found

        Message retrievedMessage = optionalBenefit.get();
        retrievedMessage.setDescription_message("A excuse");
        retrievedMessage.setId_archive(1);

        Message updatedMessage = message_repo.save(retrievedMessage);

        assertThat(updatedMessage.getId_message()).isEqualTo(savedMessage.getId_message()); // Verify ID remains the same
        assertThat(updatedMessage.getId_archive()).isEqualTo(1); // Verify updated name
        assertThat(updatedMessage.getDescription_message()).isEqualTo("A excuse"); // Verify updated description

        // Intentional error: Uncomment the following line to intentionally fail the test
        // assertThat(updatedBenefit.getNom_benefit()).isEqualTo("incorrect value");
    }

    @Test
    public void testDeleteMessage() {
        // Given
        Message message = Message.builder()
                .id_message(16)
                .id_user(1)
                .id_archive(2)
                .typeMessage(1)
                .description_message("A report")
                .date_send(LocalDate.of(2024, 12, 14))
                .build();

        message_repo.save(message);
        message_repo.deleteById(message.getId_message());
        Optional<Message> messageReturn = message_repo.findById(message.getId_message());

        assertThat(messageReturn).isEmpty();

    }



}





