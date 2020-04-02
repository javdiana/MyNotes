package com.example.notes.util;

import com.example.notes.entity.Note;

import static com.example.notes.application.RealmApplication.realm;

public class DatabaseUtil {

    public static long getLastIdFromDatabase() {
        Number maxId =  realm.where(Note.class).max("id");
        if(maxId != null && (Long) maxId >= 0) {
            return maxId.longValue();
        } else {
            return 0;
        }
    }
}
