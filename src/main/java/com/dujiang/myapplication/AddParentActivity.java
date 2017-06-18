package com.dujiang.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dujiang.myapplication.dao.FileDao;
import com.dujiang.myapplication.vo.Phone;

public class AddParentActivity extends AppCompatActivity {

    private EditText etPhone,etName;
    private Phone phone ;
    private Button btnSave,btnCancel;
    private FileDao fileDao;
    private ImageView bingPicImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent);


        phone = new Phone();
        fileDao = new FileDao(this);
        //加载BING上的每日一图
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String bingPic = prefs.getString("bing_pic", null);
        Glide.with(this).load(bingPic).into(bingPicImg);
        //获取控件
        etName = (EditText) findViewById(R.id.et_parent_name);
        etPhone = (EditText) findViewById(R.id.et_parent_phone);

        btnSave = (Button) findViewById(R.id.btn_parent_save);
        btnCancel = (Button) findViewById(R.id.btn_parent_cancel);

        btnSave.setOnClickListener(new OnClick());
        btnCancel.setOnClickListener(new OnClick());
    }
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_parent_save:

                    phone.setPhone(etPhone.getText().toString());
                    String name = etName.getText().toString();
                    if (TextUtils.isEmpty(name)){
                        phone.setName("未知用户");
                    }else {
                        phone.setName(etName.getText().toString());
                    }
                    if (fileDao.addPhone(phone)<0){
                        Toast.makeText(AddParentActivity.this,"没有成功添加...",Toast.LENGTH_LONG).show();
                    }else {
                        if ("".equals(phone.getPhone())){
                            Toast.makeText(AddParentActivity.this, "请输入手机号", Toast.LENGTH_LONG).show();
                            return;
                        }


                        Toast.makeText(AddParentActivity.this,"已经成功添加...",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddParentActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                    finish();
                    break;
                case R.id.btn_parent_cancel:
                    Intent intent = new Intent(AddParentActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    }

    //点击返回键无效
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddParentActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
