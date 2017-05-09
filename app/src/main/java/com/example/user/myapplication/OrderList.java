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

import java.util.ArrayList;

import static com.example.user.myapplication.Room.FOOD_NAME;
import static com.example.user.myapplication.Room.FOOD_PRICE;
import static com.example.user.myapplication.Room.USER_NAME;

public class OrderList extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        lv = (ListView)findViewById(R.id.LV);
        tv = (TextView)findViewById(R.id.TV_TOTAL);

        Intent intent = getIntent();
        ex = intent.getIntExtra("isExist", -1);
    }

    protected void onResume(){
        super.onResume();
        DBOpenHelper openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+OrderDB.ORDERTABLE, null);
        c.moveToFirst();
        cont = c.getCount();
        if(cont!=0){
            ArrayList<MemberOrder> member = new ArrayList<MemberOrder>();
            String [] names = c.getColumnNames();

            c.moveToFirst();
            for(int i=0; i<c.getCount(); i++) {
                user = c.getString(c.getColumnIndex("title"));
                food = c.getString(c.getColumnIndex("body"));
                price = c.getString(c.getColumnIndex("price"));

                if(food!=null && user!=null && price!=null){
                    int i1 = Integer.parseInt(price);
                    total = total + i1;
                    totalprice = Integer.toString(total);
                    tv.setText(totalprice+" 元");

                    member.add(new MemberOrder(user, food, price));
                }
                c.moveToNext();
            }
            OrderArrayAdapter adapter = new OrderArrayAdapter(this, member);
            lv.setAdapter(adapter);
        }

    }
}
