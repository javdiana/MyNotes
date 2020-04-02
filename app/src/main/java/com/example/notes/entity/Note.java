package com.example.notes.entity;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {
    @PrimaryKey
    private Long id;
    private String textNote;
    private String ownerNote;
    private Boolean isDone;

    public Note() {

    }

    public Note(Long id, String textNote, Boolean isDone) {
        this.id = id;
        this.textNote = textNote;
        ownerNote = "Diana Yavorska";

        this.isDone = isDone != null;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerNote(String ownerNote) {
        this.ownerNote = ownerNote;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getOwnerNote() {
        return ownerNote;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return getId().equals(note.getId()) &&
                Objects.equals(getTextNote(), note.getTextNote()) &&
                Objects.equals(getOwnerNote(), note.getOwnerNote()) &&
                Objects.equals(isDone, note.isDone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTextNote(), getOwnerNote(), isDone);
    }
}
