package com.dujiang.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dujiang.myapplication.util.SQliteHelper;
import com.dujiang.myapplication.vo.Account;
import com.dujiang.myapplication.vo.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dujiang0311 on 2017/2/13.
 */

public class FileDao {

    private SQliteHelper sqLiteHelper;

    private SQLiteDatabase db;

    public FileDao(Context context) {
        sqLiteHelper = new SQliteHelper(context);
    }

    //新增家属电话删除功能
    public int phoneDelete(Integer id){
        db = sqLiteHelper.getWritableDatabase();
        return db.delete("phone","id=?",new String[]{String.valueOf(id)});
    }
    //新增家属电话修改
    public int phoneUpdate(int id,String name,String phone){
        db = sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("phone",phone);
        return db.update("phone",cv,"id=?",new String[]{String.valueOf(id)});
    }
    //新增修改功能
    public int accountUpadate(int id, String type, String userid, String pwd, String other) {
        db = sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("type", type);
        cv.put("userid", userid);
        cv.put("pwd", pwd);
        cv.put("other", other);
        return db.update("account", cv, "id=?", new String[]{String.valueOf(id)});

    }

    //新增我的家属电话查询功能
    public Long addPhone(Phone phone) {
        db = sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",phone.getName());
        cv.put("phone", phone.getPhone());
        return db.insert("phone", null, cv);
    }

    //新增删除功能
    public int accountDelete(Integer id) {
        db = sqLiteHelper.getWritableDatabase();
        return db.delete("account", "id=?", new String[]{String.valueOf(id)});
    }

    //我的亲友Phone查询功能
    public List<Phone> myPhone() {
        List<Phone> list = new ArrayList<Phone>();
        db = sqLiteHelper.getReadableDatabase();
        Cursor curse = db.query("phone", new String[]{"id", "name", "phone"}, null, null, null, null, null);
        while (curse.moveToNext()) {
            Phone phone = new Phone();
            phone.setId(curse.getInt(0));
            phone.setName(curse.getString(1));
            phone.setPhone(curse.getString(2));
            list.add(phone);
        }
        return list;
    }

    //新增我的账户查询功能
    public List<Account> myAccount() {
        List<Account> list = new ArrayList<Account>();
        db = sqLiteHelper.getReadableDatabase();
        Cursor curse = db.query("account", new String[]{"id", "type", "userid", "pwd", "other"}, null, null, null, null, null);
        while (curse.moveToNext()) {
            Account account = new Account();
            account.setId(curse.getInt(0));
            account.setType(curse.getString(1));
            account.setUserId(curse.getString(2));
            account.setPwd(curse.getString(3));
            account.setOther(curse.getString(4));
            list.add(account);
        }
        return list;
    }

    //新增我的账户新增功能
    public Long addAcount(Account account) {
        /*由于当前需要实现的是添加功能，所以我们需要调用getWritableDatabase()*/
        db = sqLiteHelper.getWritableDatabase();
        /*创建ContentValues对象*/
        ContentValues cv = new ContentValues();
        cv.put("type", account.getType());
        cv.put("userid", account.getUserId());
        cv.put("pwd", account.getPwd());
        cv.put("other", account.getOther());
        return db.insert("account", null, cv);
    }
}
