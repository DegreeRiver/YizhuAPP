package com.dujiang.myapplication.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dujiang0311 on 2017/2/13.
 */

public class SQliteHelper extends SQLiteOpenHelper {
    public SQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*上面那个太过复杂，所以需要重载一个简单的方法：通过构造方法，完成数据库的创建*/
    public SQliteHelper(Context context){
        super(context,"mydb",null,1);
    }

    //当sqliteOpenHelper中新添加了execSQL语句的时候一定要注意，将之前已经运行的MyWealth进行卸载
    /*通过OnCreate方法，实现数据表的创建*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table account (id integer primary key autoincrement,type varchar2(20),userid varchar2(20),pwd varchar2(20), other varchar2(50))");
        db.execSQL("create table phone (id integer primary key autoincrement,name varchar2(20),phone varchar2(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
