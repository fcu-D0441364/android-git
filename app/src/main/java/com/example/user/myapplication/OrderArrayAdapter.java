package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderArrayAdapter extends ArrayAdapter<MemberOrder>{
    Context context;

    public OrderArrayAdapter(Context context, ArrayList<MemberOrder> items){
        super(context, 0, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemlayout = null;

        if(convertView==null) {
            itemlayout = (LinearLayout)inflater.inflate(R.layout.who_order, null);
        }
        else{
            itemlayout = (LinearLayout)convertView;
        }

        MemberOrder member = (MemberOrder)getItem(position);

        TextView v1 = (TextView)itemlayout.findViewById(R.id.who);
        v1.setText("成員 : "+member.name);
        TextView v2 = (TextView)itemlayout.findViewById(R.id.order);
        v2.setText("訂購 : "+member.order);
        TextView v3 = (TextView)itemlayout.findViewById(R.id.price);
        v3.setText("價錢 : "+member.price);

        return itemlayout;
    }
}
