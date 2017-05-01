package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.BTN_NewRoom);
        btn.setOnClickListener(newRoom);
        btn = (Button)findViewById(R.id.BTN_RoomList);
        btn.setOnClickListener(roomList);
    }

    private OnClickListener newRoom = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            intent.setClass(MainActivity.this,NewRoom.class);
            startActivity(intent);
        }
    };

    private OnClickListener roomList = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            intent.setClass(MainActivity.this,RoomList.class);
            startActivity(intent);
        }
    };

}
