package com.example.recipe.user;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_USER = "User";
    public static final String CREATE_USER= "create table " + TABLE_USER+ " ("
            + "user_email text primary key autoincrement, "
            + "user_password text) ";

    public UserDBHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据表
        Log.i("DataBase","create table User");
       sqLiteDatabase.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            default:
        }
    }
}

