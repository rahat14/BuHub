package com.metacodersbd.myapplication;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
