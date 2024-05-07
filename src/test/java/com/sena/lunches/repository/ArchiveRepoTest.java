package com.sena.lunches.repository;

import com.sena.lunches.entities.Archive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

@ActiveProfiles("test")
@AutoConfigureTestDatabase
@Rollback(false)
public class ArchiveRepoTest {
    @Autowired
    private Archive_repo archive_repo;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testSaveBenefit() {
        //function
        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(20);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf(byteArrayPdf);

        // When
        Archive savedArchive = entityManager.persist(archive);
        entityManager.flush(); // Forcibly flush the data to the database

        // Then
        assertThat(savedArchive.getId_archive()).isNotNull();// Check if ID is assigned after persisting

        // Optional: Verify if the benefit can be retrieved from the repository

        Optional<Archive> retrievedArchive = archive_repo.findById(savedArchive.getId_archive());
        assertThat(retrievedArchive).isPresent(); // Check if benefit is found
        assertThat(retrievedArchive.get().getName_archive()).isEqualTo("Identity card"); // Verify attribute value
    }

    @Test
    public void testGetArchive(){
        //function
        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(16);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf(byteArrayPdf);

        Archive archive2 = new Archive();
        archive.setId_archive(15);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card 2");
        archive.setArchive_pdf(byteArrayPdf);

        archive_repo.save(archive);
        archive_repo.save(archive2);

        List<Archive> archiveList = archive_repo.findAll();

        assertThat(archiveList).isNotNull();
        assertThat(archiveList.size()).isEqualTo(3);
    }



    @Test
    public void testFindArchive() {

        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(16);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf(byteArrayPdf);

        // When
        Archive savedArchive = archive_repo.save(archive);

        // Then
        assertThat(savedArchive.getId_archive()).isNotNull(); // Verifica que el ID se haya asignado correctamente

        Optional<Archive> retrievedArchiveOptional = archive_repo.findById(savedArchive.getId_archive());

        assertThat(retrievedArchiveOptional).isPresent();
        Archive retrievedArchive = retrievedArchiveOptional.get();
        assertThat(retrievedArchive.getTypeDoc()).isEqualTo("application/pdf"); // Verifica el tipo del documento


    }

    @Test
    public void testUpdateArchive() {
        //function
        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(10);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf(byteArrayPdf);

        // When
        Archive savedArchive = archive_repo.save(archive);

        // Retrieve the saved Archive from the repository
        Optional<Archive> optionalArchive = archive_repo.findById(savedArchive.getId_archive());
        assertThat(optionalArchive).isPresent(); // Verify that the Archive is found

        Archive retrievedArchive = optionalArchive.get();
        retrievedArchive.setName_archive("Identity card 2");
        retrievedArchive.setArchive_pdf(byteArrayPdf);

        Archive updatedArchive = archive_repo.save(retrievedArchive);

        assertThat(updatedArchive.getId_archive()).isEqualTo(savedArchive.getId_archive()); // Verify ID remains the same
        assertThat(updatedArchive.getName_archive()).isEqualTo("Identity card 2"); // Verify updated name
        assertThat(updatedArchive.getArchive_pdf()).isEqualTo(byteArrayPdf); // Verify updated description

        // Intentional error: Uncomment the following line to intentionally fail the test
        //assertThat(updatedArchive.getName_archive()).isEqualTo("incorrect value");


    }


    @Test
    public void testDeleteBenefit() {
        //function
        byte[] byteArray = new byte[10];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) i;
        }
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] byteArrayPdf = new byte[buffer.remaining()];
        buffer.get(byteArrayPdf);

        // Given
        Archive archive = new Archive();
        archive.setId_archive(16);
        archive.setTypeDoc("application/pdf");
        archive.setName_archive("Identity card");
        archive.setArchive_pdf(byteArrayPdf);

        // When
        Archive savedArchive = archive_repo.save(archive);

        archive_repo.save(archive);
        archive_repo.deleteById(archive.getId_archive());
        Optional<Archive> archiveReturn = archive_repo.findById(archive.getId_archive());

        assertThat(archiveReturn).isEmpty();

    }

}





