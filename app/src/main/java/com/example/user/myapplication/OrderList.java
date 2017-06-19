package com.example.user.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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

import static com.example.user.myapplication.Room.FOOD_NAME;
import static com.example.user.myapplication.Room.FOOD_PRICE;
import static com.example.user.myapplication.Room.ROOM_OWNER;
import static com.example.user.myapplication.Room.USER_NAME;

public class OrderList extends AppCompatActivity {
    DatabaseReference data;
    DatabaseReference MyRef;
    SQLiteDatabase db;
    String totalprice;
    int total=0;
    int cont=0;
    ListView lv;
    TextView tv;
    int ex;
    String user;
    String food;
    String price;
    String owner;
    public static boolean DataEX=true;
    ArrayList<MemberOrder> member = new ArrayList<MemberOrder>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        lv = (ListView)findViewById(R.id.LV);
        tv = (TextView)findViewById(R.id.TV_TOTAL);

        data = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        ex = intent.getIntExtra("isExist", -1);
        owner = intent.getStringExtra(ROOM_OWNER);
        MyRef = data.child("Room").child(owner);
    }

    private void setEx(boolean ex){
        DataEX = ex;
    }
    private Boolean getEx(){
        return DataEX;
    }

    protected void onResume(){
        super.onResume();
        MyRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("OrderList").exists()){
                    boolean EX = true;
                    setEx(EX);
                }
                else{
                    boolean EX = false;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //if(DataEX==true){
            MyRef = data.child("Room").child(owner).child("OrderList");


            MyRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        DataSnapshot whoOrder = ds.child("Name");
                        DataSnapshot orderWhat = ds.child("Order");
                        DataSnapshot orderPrice = ds.child("Price");
                        user = (String)whoOrder.getValue();
                        food = (String)orderWhat.getValue();
                        price = (String)orderPrice.getValue();

                        int i1 = Integer.parseInt(price);
                        total = total + i1;
                        totalprice = Integer.toString(total);
                        tv.setText(totalprice+"元");
                        member.add(new MemberOrder(user, food, price));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            OrderArrayAdapter adapter = new OrderArrayAdapter(this, member);
            lv.setAdapter(adapter);
        //}

    }
    private void getOrder(){

    }
}