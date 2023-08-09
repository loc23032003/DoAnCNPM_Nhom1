package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements RoomAdapter.Listener{
    DBHelperroom dbHelperroom;

    RecyclerView rvRoom;
    ArrayList<Room> rooms;
    Room room;
    TextView tv;
    RoomAdapter roomAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rvRoom = findViewById(R.id.rvRoom);

        rooms = new ArrayList<>();

        getSupportActionBar().setTitle(" ");

        tv= findViewById(R.id.textView2);


        roomAdapter = new RoomAdapter(SearchActivity.this, rooms, SearchActivity.this);

        rvRoom.setAdapter(roomAdapter);
        rvRoom.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        rvRoom.addItemDecoration(new DividerItemDecoration(SearchActivity.this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mnu_search, menu);
        menu.findItem(R.id.mnuSearch).expandActionView();

        SearchView searchView = (SearchView) menu.findItem(R.id.mnuSearch).getActionView();
        searchView.setIconified(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                roomAdapter.getFilter().filter(newText);
                if(roomAdapter.getItemCount() > 0){
                    tv.setVisibility(View.GONE);
                }
                if(newText.isEmpty()){
                    tv.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        return true;
    }



    @Override
    public void OnItemListener(int pos, Room room) {

    }

    @Override
    public boolean OnCreateOptionsMenu(Menu menu) {
        return false;
    }


    @Override
    public void OnDeleteListener(int pos) {

    }

    @Override
    public void OnDeleteListener(Room room) {
        dbHelperroom.deleteRoom(room);
        rooms.clear();
        rooms.addAll(dbHelperroom.getRooms());
        roomAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnEditListener(int pos, Room room) {

    }

    @Override
    public void OnEditListener(Room room) {

    }
}
