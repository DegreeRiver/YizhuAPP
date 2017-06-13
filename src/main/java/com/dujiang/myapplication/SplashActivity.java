package com.dujiang.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.dujiang.myapplication.util.ShareUtils;
import com.dujiang.myapplication.util.StaticClass;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
            if (msg.what == 0) {
                textView.setText(getCount() + "");
                handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 1000);
                animation.reset();
                textView.startAnimation(animation);
            }
        }
    };
    //倒计时控件声明
    private TextView textView;

    private int count = 3;

    private Animation animation;
    // private Button btnGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = (TextView) findViewById(R.id.tv_advt);
        //先声明Handler，再书写下面的延迟。

        //延时
        handler.sendEmptyMessageDelayed(0, 500);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);

    }

    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }


    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            //是第一次运行
            return true;
        } else {
            return false;
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
