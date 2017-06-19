package com.example.user.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import static com.example.user.myapplication.NewRoom.KEY_DEADLINE;
import static com.example.user.myapplication.NewRoom.KEY_LOCATION;
import static com.example.user.myapplication.NewRoom.KEY_NAME;
import static com.example.user.myapplication.NewRoom.KEY_RESTAURANT;

public class RoomList extends AppCompatActivity {
    public static String R_NAME = "rn";
    public static String R_STORE = "rs";
    public static String R_LOCATION = "rl";
    public static String R_DEADLINE = "rd";
    ListView lv;
    String name;
    String order;
    String location;
    String time;
    SQLiteDatabase db;
    ArrayList<Roomitem> roomList = new ArrayList<Roomitem>();
    DatabaseReference Rdata;
    DatabaseReference RoRef;
    private static final int LIST_FKU = 1;
    RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        lv = (ListView)findViewById(R.id.roomlist);
        Rdata = FirebaseDatabase.getInstance().getReference("Room");
        RoRef = Rdata;
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_FKU: {
                    ArrayList<Roomitem> pets = (ArrayList<Roomitem>)msg.obj;
                    refreshRoomList(pets);
                    break;
                }
            }
        }
    };

    private void refreshRoomList(ArrayList<Roomitem> pets) {
        adapter.clear();
        adapter.addAll(pets);
    }

    protected void onResume(){
        super.onResume();
        RoRef = Rdata;
        RoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    DataSnapshot whoCreate = ds.child("Creator");
                    DataSnapshot whereBuy = ds.child("Restaurant");
                    DataSnapshot whereTake = ds.child("Location");
                    DataSnapshot whenDead = ds.child("Deadline");
                    name = (String)whoCreate.getValue();
                    order = (String)whereBuy.getValue();
                    location = (String)whereTake.getValue();
                    time = (String)whenDead.getValue();

                    roomList.add(new Roomitem(name, order, location, time));


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter =new RoomAdapter(this, roomList);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(iclick);

        Message msg = new Message();
        msg.what = LIST_FKU;
        msg.obj = roomList;
        handler.sendMessage(msg);

    }
    OnItemClickListener iclick = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> av, View v, int position, long id) {
            TextView content = (TextView) v.findViewById(R.id.RoomName);
            name = content.getText().toString();
            content = (TextView) v.findViewById(R.id.order);
            order = content.getText().toString();
            content = (TextView) v.findViewById(R.id.location);
            location = content.getText().toString();
            content = (TextView) v.findViewById(R.id.deadline);
            time = content.getText().toString();

            Intent intent = new Intent();
            intent.setClass(RoomList.this, Room.class);
            intent.putExtra(KEY_NAME, name);
            intent.putExtra(KEY_RESTAURANT, order);
            intent.putExtra(KEY_LOCATION, location);
            intent.putExtra(KEY_DEADLINE, time);
            startActivity(intent);
        }
    };


}
