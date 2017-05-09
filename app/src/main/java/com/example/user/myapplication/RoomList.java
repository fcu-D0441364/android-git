package com.example.user.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


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
    ArrayList<Roomitem> roomlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        lv = (ListView)findViewById(R.id.roomlist);

    }

    protected void onResume(){
        super.onResume();

        roomlist = new ArrayList<Roomitem>();

        RoomOP openhelper = new RoomOP(this);
        db = openhelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+OrderDB.ROOMTABLE, null);

        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++) {
            name = c.getString(c.getColumnIndex("title"));
            order = c.getString(c.getColumnIndex("body"));
            location = c.getString(c.getColumnIndex("price"));
            time = c.getString(c.getColumnIndex("deadline"));


            if(name!=null && order!=null && location!=null && time!=null){
                roomlist.add(new Roomitem(name,order,location,time));
            }
            c.moveToNext();
        }
        RoomAdapter adapter =new RoomAdapter(this, roomlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(iclick);

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
