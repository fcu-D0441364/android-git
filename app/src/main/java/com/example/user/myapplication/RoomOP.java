package com.example.user.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anley on 2017/5/9.
 */

public class RoomOP extends SQLiteOpenHelper {
    public RoomOP(Context context) {
        super(context, "room.db", null, 1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + OrderDB.ROOMTABLE + "(title, body, price, deadline);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
