package com.example.notes.repository;

import com.example.notes.entity.Note;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

public interface CrudRepository {
    void save(RealmObject object);

    void update(Note note);

    void delete(Long id);

    void saveAll(List<RealmObject> objects);

    RealmObject getObject(Long id);

    RealmResults<? extends RealmObject> getAllObjects();

}
