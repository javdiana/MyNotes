package com.example.notes.application;

import android.app.Application;

import io.realm.Realm;

public class RealmApplication extends Application {
    public static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }
}
