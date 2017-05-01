package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Room extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent = getIntent();
        String temp;
        TextView tv = (TextView)findViewById(R.id.TV_NAME);
        temp = intent.getStringExtra("KEY_NAME");
        tv.setText("主揪人姓名 : "+temp);
        tv = (TextView)findViewById(R.id.TV_RESTAURANT);
        temp = intent.getStringExtra("KEY_RESTAURANT");
        tv.setText("訂餐餐廳 : "+temp);
        tv = (TextView)findViewById(R.id.TV_LOCATION);
        temp = intent.getStringExtra("KEY_LOCATION");
        tv.setText("取餐地點 : "+temp);
    }

    private OnClickListener orderList = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Room.this,OrderList.class);
            startActivity(intent);
        }
    };

    private OnClickListener order = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Room.this,Order.class);
            startActivity(intent);
        }
    };
}
