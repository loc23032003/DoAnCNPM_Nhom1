package com.example.firebaseauth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelperroom {
    String DB_NAME = "DBHelperroom.db";
    SQLiteDatabase db;
    Context context;

    public DBHelperroom(Context context) {
        this.context = context;
    }

    public SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }


    public void CopyDatabaseFromAssets() {
        File dbFile = context.getDatabasePath(DB_NAME);
        if (!dbFile.exists()) {
            try {
                InputStream is = context.getAssets().open(DB_NAME);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0) {
                    os.write(buffer);
                }

                os.flush();
                os.close();
                is.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Room> getRooms() {
        ArrayList<Room> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Room";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String maPhong = cursor.getString(1);
            String loaiPhong = cursor.getString(2);
            String gia = cursor.getString(3);

            String image = cursor.getString(4);

            Room room = new Room(id, maPhong, loaiPhong,gia , image);
            tmp.add(room);
        }

        db.close();

        return tmp;
    }


    //    // insert into Contact()
//    // values(?,?,?)
//
    public long insertRoom(Room room) {
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maPhong", room.getMaPhong());
        contentValues.put("loaiPhong", room.getLoaiPhong());
        contentValues.put("gia", room.getGiaTien());
        contentValues.put("image", room.getImage());

        long tmp = db.insert("Room", "", contentValues);
        db.close();
        return tmp;
    }

    //    //update Contact
//    //set  ?
//    //where ?
//
//
    public long updateRoom(Room room) {
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maPhong", room.getMaPhong());
        contentValues.put("loaiPhong", room.getLoaiPhong());
        contentValues.put("gia", room.getGiaTien());
        contentValues.put("image", room.getImage());
        long tmp = db.update("Room", contentValues, "id=" + room.getId(), null);
        db.close();
        return tmp;
    }

    //
//    // delete from Contact
//    //where ?
//
    public long deleteRoom(Room room) {
        db = openDB();
        long tmp = db.delete("Room", "id =" + room.getId(), null);
        db.close();
        return tmp;
    }
}
