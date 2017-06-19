package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewRoom extends AppCompatActivity {

    public static String KEY_NAME = "KEY_NAME";
    public static String KEY_RESTAURANT = "KEY_RESTAURANT";
    public static String KEY_LOCATION = "KEY_LOCATION";
    public static String KEY_DEADLINE = "KEY_DEADLINE";

    private DatabaseReference myRef;
    EditText et_name;
    EditText et_restaurant;
    EditText et_location;
    EditText et_deadline;
    SQLiteDatabase db;
    String name;
    String restaurant;
    String location;
    String deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);

        et_name = (EditText)findViewById(R.id.ET_Name);
        et_restaurant = (EditText)findViewById(R.id.ET_Restaurant);
        et_location = (EditText)findViewById(R.id.ET_Location);
        et_deadline = (EditText)findViewById(R.id.ET_Deadline);

        Button btn = (Button)findViewById(R.id.BTN_Cancel);
        btn.setOnClickListener(cancel);
        btn = (Button)findViewById(R.id.BTN_Create);
        btn.setOnClickListener(create);
        myRef = FirebaseDatabase.getInstance().getReference();

    }

        private OnClickListener cancel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };

        private OnClickListener create = new OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_name.getText().toString();
                restaurant = et_restaurant.getText().toString();
                location = et_location.getText().toString();
                deadline = et_deadline.getText().toString();
                Intent intent = new Intent();

                intent.putExtra(KEY_NAME,name);
                intent.putExtra(KEY_RESTAURANT, restaurant);
                intent.putExtra(KEY_LOCATION, location);
                intent.putExtra(KEY_DEADLINE, deadline);
                intent.setClass(NewRoom.this,Room.class);

                RoomInfo roominfo = new RoomInfo(name, restaurant, location, deadline);

                myRef.child("Room").child(name).setValue(roominfo);

                /*RoomOP openhelper = new RoomOP(NewRoom.this);
                db = openhelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("title", name);
                cv.put("body", restaurant);
                cv.put("price", location);
                cv.put("deadline", deadline);
                db.insert(OrderDB.ROOMTABLE, null, cv);*/


                startActivity(intent);
            }
        };




}
