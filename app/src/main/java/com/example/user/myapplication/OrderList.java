package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        TextView TV = (TextView)findViewById(R.id.TV);

        String ori;
        String temp;
        ori =TV.getEditableText().toString();
        Intent intent = getIntent();
        temp = intent.getStringExtra("KEY_ORDER");
        TV.setText(ori+temp);
    }
}
