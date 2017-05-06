package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewRoom extends AppCompatActivity {

    public static String KEY_NAME = "KEY_NAME";
    public static String KEY_RESTAURANT = "KEY_RESTAURANT";
    public static String KEY_LOCATION = "KEY_LOCATION";
    public static String KEY_DEADLINE = "KEY_DEADLINE";

    EditText et_name;
    EditText et_restaurant;
    EditText et_location;
    EditText et_deadline;
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
                String name = et_name.getText().toString();
                String restaurant = et_restaurant.getText().toString();
                String location = et_location.getText().toString();
                String deadline = et_deadline.getText().toString();
                Intent intent = new Intent();

                intent.putExtra(KEY_NAME,name);
                intent.putExtra(KEY_RESTAURANT, restaurant);
                intent.putExtra(KEY_LOCATION, location);
                intent.putExtra(KEY_DEADLINE, deadline);
                intent.setClass(NewRoom.this,Room.class);
                startActivity(intent);
            }
        };

}
