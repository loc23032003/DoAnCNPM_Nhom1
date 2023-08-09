package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Option extends AppCompatActivity {

    ImageView imgclient, imgadmin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        imgadmin = findViewById(R.id.imgadmin);
        imgclient = findViewById(R.id.imgclient);

        imgclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, Login.class);
                startActivity(intent);
            }
        });

        imgadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, Editroom.class);
                startActivity(intent);
            }
        });

    }
}