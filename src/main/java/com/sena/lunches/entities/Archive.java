package com.sena.lunches.entities;

import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;



@Data
@Entity
public class Archive {
    @Id
    /*@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")*/
    private int id_archive;
    private String typeDoc;
    private String name_archive;
    private  byte[] archive_pdf;



   public Archive(String name_archive, String typeDoc, byte[] archive_pdf) {

       this.name_archive = name_archive;
       this.typeDoc = typeDoc;
       this.archive_pdf = archive_pdf;
   }

    public Archive() {

    }

    public int getId_archive() {
        return id_archive;
    }

    public void setId_archive(int id_archive) {
        this.id_archive = id_archive;
    }
    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public  String getName_archive() {
        return name_archive;
    }

    public void setName_archive(String name_archive) {
        this.name_archive = name_archive;
    }

    public  byte[] getArchive_pdf() {
        return archive_pdf;
    }

    public void setArchive_pdf(byte[] archive_pdf) {
        this.archive_pdf = archive_pdf;
    }


}
