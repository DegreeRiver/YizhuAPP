package com.dujiang.myapplication.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 项目名：  OwnProject
 * 包名：    com.dujiang.abhorweather.util
 * 创建者：  Dujiang0311
 * 创建时间：2017/6/6 21:21
 * 描述：    网络工具
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
