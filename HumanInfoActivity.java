package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HumanInfoActivity extends AppCompatActivity {

    ImageView ivContact;
    TextView ivMaphong , ivLoaiphong, ivGia;
    TextView ivContactLast;

    Room room;
    Button btnDatphong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);


        Intent intent = getIntent();

        room = (Room) intent.getSerializableExtra("room");

        ivContact = findViewById(R.id.imgProfile);
       ivMaphong = findViewById(R.id.tvMaphong);
       ivLoaiphong = findViewById(R.id.tvLoaiphong);
        ivGia =findViewById(R.id.tvGia);
        btnDatphong = (Button) findViewById(R.id.btnDatphong);
        btnDatphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HumanInfoActivity.this, Infor.class);
                intent.putExtra("room",room);
                startActivity(intent);
            }
        });


//        ivContact.setImageResource(contact.getImage());
        ivMaphong.setText(room.getMaPhong());
        ivLoaiphong.setText(room.getLoaiPhong());
        ivGia.setText(room.getGiaTien());


       // Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
