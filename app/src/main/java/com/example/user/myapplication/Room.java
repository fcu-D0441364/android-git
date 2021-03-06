package com.example.user.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.user.myapplication.NewRoom.KEY_DEADLINE;
import static com.example.user.myapplication.NewRoom.KEY_LOCATION;
import static com.example.user.myapplication.NewRoom.KEY_NAME;
import static com.example.user.myapplication.NewRoom.KEY_RESTAURANT;
import static com.example.user.myapplication.Order.KEY_NAMEO;
import static com.example.user.myapplication.Order.KEY_ORDER;
import static com.example.user.myapplication.Order.KEY_PRICE;

public class Room extends AppCompatActivity {
    public static String FOOD_NAME = "FOOD";
    public static String FOOD_PRICE = "PRICE";
    public static String USER_NAME = "NAME";
    public static String ROOM_OWNER = "ROOM";
    private String ordername=null;
    private String orderprice=null;
    private String orderssname=null;
    public String NAME;
    SQLiteDatabase db;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Button btn = (Button)findViewById(R.id.BTN_OrderList);
        btn.setOnClickListener(orderList);
        btn = (Button)findViewById(R.id.BTN_Order);
        btn.setOnClickListener(order);
        btn = (Button)findViewById(R.id.BACKHOME);
        btn.setOnClickListener(BACK);
        Intent intent = getIntent();
        String temp;
        TextView tv = (TextView)findViewById(R.id.TV_NAME);
        NAME = intent.getStringExtra(KEY_NAME);
        tv.setText("主揪人姓名 : "+NAME);
        tv = (TextView)findViewById(R.id.TV_RESTAURANT);
        temp = intent.getStringExtra(KEY_RESTAURANT);
        tv.setText("訂餐餐廳 : "+temp);
        tv = (TextView)findViewById(R.id.TV_LOCATION);
        temp = intent.getStringExtra(KEY_LOCATION);
        tv.setText("取餐地點 : "+temp);
        tv = (TextView)findViewById(R.id.TV_DEADLINE);
        temp = intent.getStringExtra(KEY_DEADLINE);
        tv.setText("截止時間 : "+temp);
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    private OnClickListener orderList = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            if(ordername==null && orderprice==null) intent.putExtra("isExist", -1);
            else intent.putExtra("isExist", 1);
            intent.putExtra(ROOM_OWNER, NAME);
            intent.setClass(Room.this,OrderList.class);
            startActivity(intent);
        }
    };
    private OnClickListener BACK = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Room.this, MainActivity.class);
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

        //myRef = FirebaseDatabase.getInstance().getReference();
        /*DBOpenHelper openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();*/

        switch(requestCode){
            case submitnum :
                ordername = intent.getStringExtra(KEY_ORDER);
                orderprice = intent.getStringExtra(KEY_PRICE);
                orderssname = intent.getStringExtra(KEY_NAMEO);
                if(ordername!=null && orderprice!=null && orderssname!=null) {
                    OrderInfo OI = new OrderInfo(orderssname, ordername, orderprice);
                    myRef.child("Room").child(NAME).child("OrderList").child(orderssname).setValue(OI);
                }
        }
    }

    /*protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        if(intent!=null){
            String temp;
            TextView tv = (TextView)findViewById(R.id.TV_NAME);
            NAME = intent.getStringExtra(RoomList.R_NAME);
            tv.setText("主揪人姓名 : "+NAME);
            tv = (TextView)findViewById(R.id.TV_RESTAURANT);
            temp = intent.getStringExtra(RoomList.R_STORE);
            tv.setText("訂餐餐廳 : "+temp);
            tv = (TextView)findViewById(R.id.TV_LOCATION);
            temp = intent.getStringExtra(RoomList.R_LOCATION);
            tv.setText("取餐地點 : "+temp);
            tv = (TextView)findViewById(R.id.TV_DEADLINE);
            temp = intent.getStringExtra(RoomList.R_DEADLINE);
            tv.setText("截止時間 : "+temp);
        }
    }*/

}
