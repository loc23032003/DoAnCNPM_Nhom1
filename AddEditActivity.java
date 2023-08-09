package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class AddEditActivity extends AppCompatActivity {

    TextInputLayout tiMaphong, tiLoaiPhong, tiGia;
    TextInputEditText edMaphong, edLoaiphong, edGia;
    Room roomEdit;
    Button btnadd;
    ImageView imgAvatar;

    int flag;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tiMaphong = findViewById(R.id.tiMaphong);
        tiLoaiPhong = findViewById(R.id.tiLoaiphong);
       tiGia = findViewById(R.id.tiGia);
       btnadd= findViewById(R.id.btnadd);


        edMaphong = findViewById(R.id.edMaphong);
        edLoaiphong = findViewById(R.id.edLoaiphong);

        edGia = findViewById(R.id.edGia);



        imgAvatar = findViewById(R.id.imgProfile);

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag == 1) {

           // getSupportActionBar().setTitle(R.string.add);
        } else {
           // getSupportActionBar().setTitle(R.string.edit);
            roomEdit = (Room) intent.getSerializableExtra("room");
            edMaphong.setText(roomEdit.getMaPhong());
            edLoaiphong.setText(roomEdit.getLoaiPhong());

            edGia.setText(roomEdit.getGiaTien());

//            imgAvatar.setImageResource(humanEdit.getImage());
        }

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnadd) {
                    if (edMaphong.getText().toString().isEmpty()
                            || edLoaiphong.getText().toString().isEmpty()
                            || edGia.getText().toString().isEmpty()) {
                        tiMaphong.setError("Not null");
                        tiLoaiPhong.setError("Not null");
                        tiGia.setError("Not null");
                    } else {
                        if (flag == 1) {
                            Room room = new Room(new Random().nextInt(9999),
                                    edMaphong.getText().toString(),
                                    edLoaiphong.getText().toString(),
                                    edGia.getText().toString(),"");



                            Intent intent = new Intent();
                            intent.putExtra("room", room);
                            intent.putExtra("flag", 1);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            Room room = new Room(roomEdit.getId(),
                                    edMaphong.getText().toString(),
                                    edLoaiphong.getText().toString(),
                                    edGia.getText().toString(),
                                    roomEdit.getImage());

                            Intent intent = new Intent();
                            intent.putExtra("room", room);
                            intent.putExtra("flag", 2);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                }
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_mnu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            if (edMaphong.getText().toString().isEmpty()
                    || edLoaiphong.getText().toString().isEmpty()
                    || edGia.getText().toString().isEmpty()) {
                tiMaphong.setError("Not null");
                tiLoaiPhong.setError("Not null");
                tiGia.setError("Not null");

                return false;
            } else {

                if (flag == 1) {
                    Room room = new Room(new Random().nextInt(9999),
                            edMaphong.getText().toString(),
                            edLoaiphong.getText().toString(),
                            edGia.getText().toString(),"");


                    Intent intent = new Intent();
                    intent.putExtra("room", room);
                    intent.putExtra("flag", 1);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                else {
                    Room room = new Room(roomEdit.getId(),
                            edMaphong.getText().toString(),
                            edLoaiphong.getText().toString(),

                            edGia.getText().toString(),roomEdit.getImage());
                    Intent intent = new Intent();
                    intent.putExtra("room", room);
                    intent.putExtra("flag", 2);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}

