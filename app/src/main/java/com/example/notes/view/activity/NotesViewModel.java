package com.example.notes.view.activity;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

public class NotesViewModel {
    private NoteRepository noteRepository;

    public NotesViewModel() {
        noteRepository = new NoteRepository();
    }

    public void saveNotes(List<RealmObject> notes) {
        noteRepository.saveAll(notes);
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public void updateNote(Note newNote) {
        noteRepository.update(newNote);
    }

    public void deleteNote(Long id) {
        noteRepository.delete(id);
    }

    public RealmResults<? extends RealmObject> getNotes() {
        return noteRepository.getAllObjects();
    }
}
