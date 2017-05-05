package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.myapplication.Room.FOOD_NAME;

public class OrderList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        TextView TV = (TextView)findViewById(R.id.TV);

        Intent intent = getIntent();
        String temp = intent.getStringExtra(FOOD_NAME);
        TV.setText(TV.getText().toString()+temp);
        String ori = TV.getText().toString();
        Toast.makeText(OrderList.this, ori, Toast.LENGTH_LONG).show();
    }
}
