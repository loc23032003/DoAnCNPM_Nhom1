package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class listphong extends AppCompatActivity implements Datphong.Listener {
    RecyclerView rvRoom;
    ArrayList<Room> rooms;
    Datphong datphong;

    int position;
    FloatingActionButton fabAddContact;

    DBHelperroom dbHelperroom;

    ActivityResultLauncher<Intent> mLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData().getIntExtra("flag", 0) == 1) {
                            Room room = (Room) result.getData().getSerializableExtra("room");
                            if (dbHelperroom.insertRoom(room)>0){
                                Toast.makeText(listphong.this, "OKIE", Toast.LENGTH_LONG).show();
                            }
                            rooms.clear();
                            rooms.addAll(dbHelperroom.getRooms());
                            datphong.notifyDataSetChanged();
                        }else {
                            Room room = (Room) result.getData().getSerializableExtra("room");
                            dbHelperroom.updateRoom(room);
                            rooms.clear();
                            rooms.addAll(dbHelperroom.getRooms());
                            datphong.notifyDataSetChanged();
                        }
                    }
                }
            }
    );


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_room);

        rvRoom = findViewById(R.id.rvRooms);

        dbHelperroom = new DBHelperroom(listphong.this);
        rooms = dbHelperroom.getRooms();

        datphong = new Datphong(listphong.this, rooms, listphong.this);

        rvRoom.setAdapter(datphong);
        rvRoom.setLayoutManager(new LinearLayoutManager(listphong.this, LinearLayoutManager.VERTICAL, false));
        rvRoom.addItemDecoration(new DividerItemDecoration(listphong.this, LinearLayoutManager.VERTICAL));

        //getSupportActionBar().setTitle(R.string.room);
        dbHelperroom= new DBHelperroom(listphong.this);
        fabAddContact = findViewById(R.id.fabAddContact);
        fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listphong.this,AddEditActivity.class);
                intent.putExtra("flag",1);
                mLaucher.launch(intent);

            }
        });

    }



    @Override
    public void OnItemListener(int pos, Room room) {
        position=pos;
        Intent intent = new Intent(listphong.this,HumanInfoActivity.class);
        intent.putExtra("room",room);
        startActivity(intent);
    }

    @Override
    public boolean OnCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void OnEditListener(int pos, Room room) {

    }

    @Override
    public void OnEditListener(Room room) {

    }

    @Override
    public void OnDeleteListener(Room room) {

    }

    @Override
    public void OnDeleteListener(int pos) {

    }


}
