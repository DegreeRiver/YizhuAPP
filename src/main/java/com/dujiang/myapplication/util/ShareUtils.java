package com.dujiang.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名：  Realproject
 * 包名：    com.dujiang.realproject.utils
 * 创建者：  Dujiang0311
 * 创建时间：2017/2/21 15:16
 * 描述：    SharedPreferences封装
 */

public class ShareUtils {

     /*private void test(Context mContext){
       SharedPreferences sp = mContext.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.getString("key","未获取到");
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("key","values");
        editor.commit();*/

    public static final String NAME = "config";

    //键 值
    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    //键 默认值
    public static String getString(Context mContext, String key, String defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);

    }

    //键 值
    public static void putInt(Context mContext, String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    //键 默认值
    public static int getInt(Context mContext, String key, int defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);

    }

    //键 值
    public static void putBoolean(Context mContext, String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    //键 默认值
    public static boolean getBoolean(Context mContext, String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);

    }

    //删除  单个
    public static void deleteShare(Context mContext,String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除  全部
    public static void deleteAll(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
