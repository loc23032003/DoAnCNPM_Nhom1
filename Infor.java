package com.example.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class Infor extends AppCompatActivity {
    EditText name, email, age;
    Button insert, view;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(Infor.this, Userlist.class)});
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String ageTXT = age.getText().toString();

                Boolean checkingsertdata = DB.insertuserdate(nameTXT, emailTXT, ageTXT);
                if(checkingsertdata==true)
                {
                    Toast.makeText(Infor.this, "New Entry Insertes", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Infor.this, "New Entry NotInsertes", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}