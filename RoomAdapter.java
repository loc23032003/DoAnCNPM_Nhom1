package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.CountryVH> implements Filterable {
        Context context;

        ArrayList<Room> rooms;
        ArrayList<Room> roomsFilter;
        Listener listener;

        public RoomAdapter(Listener listener, ArrayList<Room> rooms, Context contect) {
                this.listener = listener;
                this.rooms = rooms;
                this.roomsFilter = rooms;
                this.context = contect;
        }

        @NonNull
        @Override
        public CountryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row, parent, false);

                return new CountryVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CountryVH holder, @SuppressLint("RecyclerView") int position) {
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


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                listener.OnItemListener(position, room);
                        }
                });
                holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                listener.OnEditListener(position, room);
                        }
                });
                holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                listener.OnDeleteListener(room);
                        }
                });

        }

        @Override
        public int getItemCount() {
                return rooms.size();
        }

        public Filter getFilter() {
                return new RoomFilter();
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
                        ivEdit = itemView.findViewById(R.id.ivEidt);
                        ivDelete = itemView.findViewById(R.id.ivDelete);
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
