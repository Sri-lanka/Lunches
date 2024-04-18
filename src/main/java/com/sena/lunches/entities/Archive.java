package com.sena.lunches.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Archive {
    @Id
    private int id_archive;
    private int id_excuse;
    private int id_message;
    private  String archive;


    public int getId_archive() {
        return id_archive;
    }

    public void setId_archive(int id_archive) {
        this.id_archive = id_archive;
    }

    public int getId_excuse() {
        return id_excuse;
    }

    public void setId_excuse(int id_excuse) {
        this.id_excuse = id_excuse;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }
}
