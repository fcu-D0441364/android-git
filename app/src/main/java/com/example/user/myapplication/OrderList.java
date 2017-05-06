package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.user.myapplication.Room.FOOD_NAME;
import static com.example.user.myapplication.Room.FOOD_PRICE;
import static com.example.user.myapplication.Room.USER_NAME;

public class OrderList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        ListView lv = (ListView)findViewById(R.id.LV);
        TextView tv = (TextView)findViewById(R.id.TV_TOTAL);
        String totalprice;
        int total=0;

        Intent intent = getIntent();
        String food = intent.getStringExtra(FOOD_NAME);
        String user = intent.getStringExtra(USER_NAME);
        String price = intent.getStringExtra(FOOD_PRICE);

        if(food!=null && user!=null && price!=null) {
            int i1 = Integer.parseInt(price);
            total = total + i1;
            totalprice = Integer.toString(total);
            tv.setText(totalprice);

            ArrayList<MemberOrder> member = new ArrayList<MemberOrder>();

            member.add(new MemberOrder(user, food, price));

            OrderArrayAdapter adapter = new OrderArrayAdapter(this, member);
            lv.setAdapter(adapter);
        }
    }
}
