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

public class StoreArrayAdapter extends ArrayAdapter<StoreMenu> {
    Context context;

    public StoreArrayAdapter(Context context, ArrayList<StoreMenu> items){
        super(context, 0, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemlayout = null;

        if(convertView==null) {
            itemlayout = (LinearLayout)inflater.inflate(R.layout.storelist, null);
        }
        else{
            itemlayout = (LinearLayout)convertView;
        }

        StoreMenu store = (StoreMenu)getItem(position);

        ImageView p1 = (ImageView)itemlayout.findViewById(R.id.img1);
        p1.setImageResource(store.pic);
        TextView n1 = (TextView)itemlayout.findViewById(R.id.tv1);
        n1.setText(store.store);




        return itemlayout;
    }

}
