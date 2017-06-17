package com.dujiang.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dujiang.myapplication.dao.FileDao;
import com.dujiang.myapplication.vo.Account;

import java.util.Date;

public class AddAcountActivity extends AppCompatActivity {

    private EditText etType, etUserid, etPwd, etOther;
    private Button btnSave, btnCancel;
    private Account account;
    private ImageView bingPicImg;
    private FileDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acount);

        //加载BING上的每日一图
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String bingPic = prefs.getString("bing_pic", null);
        Glide.with(this).load(bingPic).into(bingPicImg);
        account = new Account();
        dao = new FileDao(this);

        //获取控件
        this.etType = (EditText) findViewById(R.id.et_add_type);
        this.etUserid = (EditText) findViewById(R.id.et_add_userid);
        this.etPwd = (EditText) findViewById(R.id.et_add_pwd);
        this.etOther = (EditText) findViewById(R.id.et_add_other);
        this.btnSave = (Button) findViewById(R.id.btn_add_save);
        this.btnCancel = (Button) findViewById(R.id.btn_add_cancel);

        btnSave.setOnClickListener(new OnClick());
        btnCancel.setOnClickListener(new OnClick());

    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_add_save:

                    account.setType(etType.getText().toString());
                    account.setUserId(etUserid.getText().toString());
                    account.setPwd(etPwd.getText().toString());
                    account.setOther(etOther.getText().toString());
                    if (dao.addAcount(account) < 0) {
                        Toast.makeText(AddAcountActivity.this, "没有成功添加...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddAcountActivity.this, "已经成功添加...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddAcountActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                    finish();
                    break;
                case R.id.btn_add_cancel:
                    Intent intent1 = new Intent(AddAcountActivity.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
            }
        }

    }
    //点击返回键无效
    @Override
    public void onBackPressed() {

        return ;

    }

}
