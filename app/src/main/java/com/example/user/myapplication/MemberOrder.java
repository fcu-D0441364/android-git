package com.example.user.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemberOrder {
    String name;
    String order;
    String price;

    MemberOrder(String name, String order, String price){
        this.name = name;
        this.order = order;
        this.price = price;
    }



}
