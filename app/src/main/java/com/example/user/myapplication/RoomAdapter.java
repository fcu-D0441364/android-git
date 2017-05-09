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

/**
 * Created by jim23 on 2017/5/9.
 */

public class RoomAdapter extends ArrayAdapter<Roomitem>

    {
        Context context;

    public RoomAdapter (Context context, ArrayList<Roomitem> items){
        super(context ,0 ,items);
        this.context = context;
    }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemlayout = null;
        if(convertView == null) {
            itemlayout = (LinearLayout)inflater.inflate(R.layout.roomlistitem, null);
        } else {
            itemlayout = (LinearLayout) convertView;
        }

        Roomitem item = (Roomitem)getItem(position);
        TextView tv_name = (TextView)itemlayout.findViewById(R.id.RoomName);
        tv_name.setText(item.name);
        TextView p1 = (TextView)itemlayout.findViewById(R.id.order);
        p1.setText(item.order);
        TextView tv_name2 = (TextView)itemlayout.findViewById(R.id.location);
        tv_name2.setText(item.location);
        TextView p2 = (TextView)itemlayout.findViewById(R.id.deadline);
        p2.setText(item.time);


        return itemlayout;
    }
    }