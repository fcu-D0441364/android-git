package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Order extends AppCompatActivity {

    public static String KEY_ORDER = "KEY_ORDER";
    public static String KEY_PRICE = "KEY_PRICE";
    EditText et_order;
    EditText et_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toast.makeText(Order.this, "HELLO", Toast.LENGTH_LONG).show();

        et_order = (EditText)findViewById(R.id.ET_order);
        et_price = (EditText)findViewById(R.id.ET_price);

        Button btn = (Button)findViewById(R.id.BTN_order);
        btn.setOnClickListener(submit);
        Button btn2 = (Button)findViewById(R.id.BTN_Cancel);
        btn2.setOnClickListener(cancel);
    }

    private OnClickListener cancel = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private OnClickListener submit = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String order = et_order.getText().toString();
            String price = et_price.getText().toString();
            Intent intent = new Intent();

            intent.setClass(Order.this, Room.class);
            intent.putExtra("KEY_ORDER",order);
            intent.putExtra("KEY_PRICE",price);
            startActivity(intent);
        }
    };
}
