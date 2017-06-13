package com.dujiang.myapplication.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dujiang0311 on 2017/2/13.
 */

public class SQLiteUserHelper extends SQLiteOpenHelper {
    public SQLiteUserHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户表
        db.execSQL("create table user(userid integer primary key autoincrement,phone text(20) not null,card text(20) not null,pwd text(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
