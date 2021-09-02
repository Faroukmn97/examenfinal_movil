package com.example.examenfinalmovil.models;

import java.io.Serializable;

public class JournalModel  implements Serializable {
    private String journal_id;
    private String portada;
    private String abbreviation;
    private String description;
    private String journalThumbnail;
    private String name;


    public JournalModel(String journal_id, String portada, String abbreviation, String description, String journalThumbnail, String name) {
        this.journal_id = journal_id;
        this.portada = portada;
        this.abbreviation = abbreviation;
        this.description = description;
        this.journalThumbnail = journalThumbnail;
        this.name = name;
    }

    public String getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(String journal_id) {
        this.journal_id = journal_id;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJournalThumbnail() {
        return journalThumbnail;
    }

    public void setJournalThumbnail(String journalThumbnail) {
        this.journalThumbnail = journalThumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JournalModel{" +
                "journal_id='" + journal_id + '\'' +
                ", portada='" + portada + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", description='" + description + '\'' +
                ", journalThumbnail='" + journalThumbnail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
