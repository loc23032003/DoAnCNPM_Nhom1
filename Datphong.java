package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Datphong extends RecyclerView.Adapter<Datphong.CountryVH> implements Filterable {
    Context context;

    ArrayList<Room> rooms;
    ArrayList<Room> roomsFilter;
    Datphong.Listener listener;

    public Datphong(Datphong.Listener listener, ArrayList<Room> rooms, Context contect) {
        this.listener = listener;
        this.rooms = rooms;
        this.roomsFilter = rooms;
        this.context = contect;
    }

    @NonNull
    @Override
    public Datphong.CountryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_room, parent, false);

        return new Datphong.CountryVH(view);
    }



    @Override
    public void onBindViewHolder(@NonNull Datphong.CountryVH holder, @SuppressLint("RecyclerView") int position) {
        Room room = roomsFilter.get(position);
//        holder.imgPhone.setImageResource(contact.getImage());
        holder.txtMaphong.setText(room.getMaPhong());
        holder.txtLoaiphong.setText(room.getLoaiPhong());
        holder.txtGia.setText(room.getGiaTien());

        try {
            InputStream is = context.getAssets().open(room.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.imgPhone.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public Filter getFilter() {
        return new Datphong.RoomFilter();
    }


    class CountryVH extends RecyclerView.ViewHolder {
        CircleImageView imgPhone;
        ImageView ivEdit, ivDelete;
        TextView txtMaphong, txtLoaiphong, txtGia;

        public CountryVH(@NonNull View itemView) {
            super(itemView);

            txtMaphong = itemView.findViewById(R.id.txtMaphong);
            txtLoaiphong = itemView.findViewById(R.id.txtLoai);
            txtGia = itemView.findViewById(R.id.txtGia);
            imgPhone = itemView.findViewById(R.id.imgPhone);

        }
    }

    class RoomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            if (charString.isEmpty()) {
                roomsFilter = rooms;
            } else {
                List<Room> filteredList = new ArrayList<>();
                for (Room row : rooms) {
                    if (row.getMaPhong().toLowerCase().contains(charString.toLowerCase()) || row.getLoaiPhong().contains(charSequence) ||  row.getLoaiPhong().contains(charSequence)) {
                        filteredList.add(row);

                    }
                }
                roomsFilter = (ArrayList<Room>) filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = roomsFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            roomsFilter = (ArrayList<Room>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    public void addContact(Room room) {
        roomsFilter.add(room);
        notifyDataSetChanged();
    }

    public void editContact(Room room, int pos) {
        roomsFilter.set(pos, room);
        notifyDataSetChanged();
    }

    public void deleteContact(int pos) {
        roomsFilter.remove(pos);
        notifyDataSetChanged();
    }

    public void deleteContact(Room room) {
        roomsFilter.remove(room);
        notifyDataSetChanged();
    }

    interface Listener {


        void OnItemListener(int pos, Room room);

        boolean OnCreateOptionsMenu(Menu menu);

        void OnEditListener(int pos, Room room);

        void OnEditListener(Room room);


        void OnDeleteListener(Room room);

        void OnDeleteListener(int pos);
    }
}