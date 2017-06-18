package com.example.user.myapplication;

/**
 * Created by Anley on 2017/6/18.
 */

public class RoomInfo {
    String Creator;
    String Restaurant;
    String Location;
    String Deadline;

    public RoomInfo(String creator, String rest, String loc, String deadline){
        this.Creator = creator;
        this.Deadline = deadline;
        this.Location = loc;
        this.Restaurant = rest;
    }


}
