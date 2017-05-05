package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.myapplication.Order.KEY_ORDER;
import static com.example.user.myapplication.Order.KEY_PRICE;

public class Room extends AppCompatActivity {
    public static String FOOD_NAME = "FOOD";
    public static String FOOD_PRICE = "PRICE";
    private String ordername;
    private String orderprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Button btn = (Button)findViewById(R.id.BTN_OrderList);
        btn.setOnClickListener(orderList);
        btn = (Button)findViewById(R.id.BTN_Order);
        btn.setOnClickListener(order);
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
            intent.putExtra(FOOD_NAME, ordername);
            intent.putExtra(FOOD_PRICE, orderprice);
            intent.setClass(Room.this,OrderList.class);
            startActivity(intent);
        }
    };

    private static final int submitnum = 1;

    private OnClickListener order = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Room.this,Order.class);
            startActivityForResult(intent, submitnum);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){
            case submitnum :
                ordername = intent.getStringExtra(KEY_ORDER);
                orderprice = intent.getStringExtra(KEY_PRICE);
        }
    }
}
