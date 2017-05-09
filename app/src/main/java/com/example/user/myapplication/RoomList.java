package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


import static com.example.user.myapplication.NewRoom.KEY_DEADLINE;
import static com.example.user.myapplication.NewRoom.KEY_LOCATION;
import static com.example.user.myapplication.NewRoom.KEY_NAME;
import static com.example.user.myapplication.NewRoom.KEY_RESTAURANT;

public class RoomList extends AppCompatActivity {

    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        Intent room = getIntent();

        String name = room.getStringExtra(KEY_NAME);
        String order = room.getStringExtra(KEY_RESTAURANT);
        String location = room.getStringExtra(KEY_LOCATION);
        String time = room.getStringExtra(KEY_DEADLINE);

        ArrayList<Roomitem> roomlist = new ArrayList<Roomitem>();
        roomlist.add(new Roomitem("團主:"+name,"餐廳:"+order,"地點:"+location,"截止時間:"+time));

        RoomAdapter adapter =new RoomAdapter(this, roomlist);
        lv = (ListView)findViewById(R.id.roomlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(iclick);

    }
    AdapterView.OnItemClickListener iclick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> av, View v, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(RoomList.this, Room.class);
            intent.putExtra("NOTEPOS", position);
            startActivity(intent);
        }
    };

}
