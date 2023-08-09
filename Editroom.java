package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

public class Editroom extends AppCompatActivity implements RoomAdapter.Listener {
    RecyclerView rvRoom;
    ArrayList<Room> rooms;
    RoomAdapter roomAdapter;
    FloatingActionButton fabAddContact;
    int position;

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
                                Toast.makeText(Editroom.this, "OKIE", Toast.LENGTH_LONG).show();
                            }
                            rooms.clear();
                            rooms.addAll(dbHelperroom.getRooms());
                            roomAdapter.notifyDataSetChanged();
                        }else {
                            Room room = (Room) result.getData().getSerializableExtra("room");
                            dbHelperroom.updateRoom(room);
                            rooms.clear();
                            rooms.addAll(dbHelperroom.getRooms());
                            roomAdapter.notifyDataSetChanged();
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

        dbHelperroom = new DBHelperroom(Editroom.this);
        rooms = dbHelperroom.getRooms();

        roomAdapter = new RoomAdapter(Editroom.this, rooms, Editroom.this);

        rvRoom.setAdapter(roomAdapter);
        rvRoom.setLayoutManager(new LinearLayoutManager(Editroom.this, LinearLayoutManager.VERTICAL, false));
        rvRoom.addItemDecoration(new DividerItemDecoration(Editroom.this, LinearLayoutManager.VERTICAL));

        //getSupportActionBar().setTitle(R.string.room);
        dbHelperroom= new DBHelperroom(Editroom.this);

        fabAddContact = findViewById(R.id.fabAddContact);
        fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Editroom.this,AddEditActivity.class);
                intent.putExtra("flag",1);
                mLaucher.launch(intent);

            }
        });
    }



    @Override
    public void OnItemListener(int pos, Room room) {
        position=pos;
        Intent intent = new Intent(Editroom.this,HumanInfoActivity.class);
        intent.putExtra("room",room);
        startActivity(intent);
    }

    @Override
    public boolean OnCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void OnEditListener(int pos, Room room) {
        position =pos;
        Intent intent = new Intent(Editroom.this, AddEditActivity.class);
        intent.putExtra("flag",2);
        intent.putExtra("room", room);
        mLaucher.launch(intent);

    }


    @Override
    public void OnEditListener(Room room) {
        Intent intent = new Intent(Editroom.this, AddEditActivity.class);
        intent.putExtra("flag",2);
        intent.putExtra("room", room);
        mLaucher.launch(intent);
    }

    @Override
    public void OnDeleteListener(int pos) {

    }


    @Override
    public void OnDeleteListener(Room room) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Editroom.this);
        builder.setTitle("Contacts");
        builder.setMessage("Delete Telephone ?");
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            dialogInterface.cancel();

        });
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            dbHelperroom.deleteRoom(room);
            rooms.clear();
            rooms.addAll(dbHelperroom.getRooms());
            roomAdapter.notifyDataSetChanged();
            dialogInterface.dismiss();

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //contactAdapter.deleteContact(pos);
    }








}
