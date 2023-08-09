package com.example.firebaseauth;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;

public class App extends Application implements Serializable {
    public static ArrayList<Room> data;

    DBHelperroom dbHelperroom;
    @Override
    public void onCreate() {
        super.onCreate();
        dbHelperroom = new DBHelperroom(this);
        dbHelperroom.CopyDatabaseFromAssets();
    }
}

