package com.example.user.myapplication;

/**
 * Created by jim23 on 2017/5/9.
 */

public class Roomitem {
    String name,time,order,location;
    Roomitem(String name, String order, String location, String time) {
        this.name = name;
        this.time=time;
        this.order=order;
        this.location=location;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getOrder() {
        return order;
    }

    public String getLocation() {
        return location;
    }
}

