package com.example.notes.repository;

import com.example.notes.entity.Note;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.example.notes.application.RealmApplication.realm;

public class NoteRepository implements CrudRepository {
    private RealmQuery<Note> query;

    public NoteRepository() {
        query = realm.where(Note.class);
    }

    @Override
    public void save(RealmObject note) {
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }

    @Override
    public void update(RealmObject newNote) {
        final Note n = (Note) newNote;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Note note = realm.where(Note.class).equalTo("id", n.getId()).findFirst();
                if (note != null) {
                    note.deleteFromRealm();
                    save(note);
                }
            }
        });
    }

    @Override
    public void delete(final Long id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Note note = realm.where(Note.class).equalTo("id", id).findFirst();

                if (note != null) {
                    note.deleteFromRealm();
                }
            }
        });
    }

    @Override
    public void saveAll(List<RealmObject> notes) {
        realm.beginTransaction();
        realm.insert(notes);
        realm.commitTransaction();
    }

    @Override
    public RealmObject getObject(Long id) {
        return query.or().equalTo("id", id).findFirst();
    }

    @Override
    public RealmResults<? extends RealmObject> getAllObjects() {
        return query.findAll();
    }
}