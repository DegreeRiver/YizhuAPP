package com.dujiang.myapplication;

import android.animation.Animator;
import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.dujiang.myapplication.util.ParticleView;

import java.util.Date;

import static android.widget.RadioButton.*;

public class MainActivity extends AppCompatActivity {

    private ImageView ivAdd, ivSearch, ivAddParent, ivSelectParent;
    private Date backTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ParticleView particleAnimator = new ParticleView(MainActivity.this, 2000);
        particleAnimator.setOnAnimationListener(new ParticleView.OnAnimationListener() {
            @Override
            public void onAnimationStart(View view, Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(View view, Animator animation) {

            }
        });

        ivAdd = (ImageView) findViewById(R.id.iv_add_acount);
        ivSearch = (ImageView) findViewById(R.id.iv_search_acount);
        ivAddParent = (ImageView) findViewById(R.id.iv_add_parent);
        ivSelectParent = (ImageView) findViewById(R.id.iv_select_parent);

        ivAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddAcount = new Intent(MainActivity.this, AddAcountActivity.class);
                startActivity(intentAddAcount);
                finish();
            }
        });
        ivSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSearchAcount = new Intent(MainActivity.this, SearchAccountActivity.class);
                startActivity(intentSearchAcount);
            }
        });
        ivAddParent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, AddParentActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        ivSelectParent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(MainActivity.this, LookPhoneActivity.class);
                startActivity(intent2);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Date currTime = new Date();
        if (backTime == null || currTime.getTime() - backTime.getTime() > 2500) {
            backTime = currTime;
            Toast.makeText(this, "再次按下返回键退出！", Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }
}
