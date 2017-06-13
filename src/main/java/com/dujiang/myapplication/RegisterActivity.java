package com.dujiang.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dujiang.myapplication.util.SQLiteUserHelper;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvBackLogin;
    private Button btnRegister;
    private CheckBox cbRemmber;
    private EditText etPhone,etCard,etPwd1,etPwd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 初始化Toolbar
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("用户注册");*/
        // 导航图标
     /*   toolbar.setNavigationIcon(R.drawable.back);
        // 导航图标点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 因为启动是使用ForResult，直接finish()即可返回
                finish();
            }
        });*/

        //获取组件
        tvBackLogin = (TextView) findViewById(R.id.reg_tv_tologin);

        btnRegister = (Button) findViewById(R.id.reg_btn);
        cbRemmber = (CheckBox) findViewById(R.id.reg_cb_rem);
        etPhone = (EditText) findViewById(R.id.reg_et_phone);
        etCard = (EditText) findViewById(R.id.reg_et_card);
        etPwd1 = (EditText) findViewById(R.id.reg_et_pwd1);
        etPwd2 = (EditText) findViewById(R.id.reg_et_pwd2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!cbRemmber.isChecked()){
                    Toast.makeText(RegisterActivity.this, "您是否已经记得密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = etPhone.getText().toString();
                String card = etCard.getText().toString();
                String pwd1 = etPwd1.getText().toString();
                String pwd2 = etPwd2.getText().toString();

                if ("".equals(phone)) {
                    Toast.makeText(RegisterActivity.this, "手机号必须填写！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(card)) {
                    Toast.makeText(RegisterActivity.this, "身份证号必须填写！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd1.equals(pwd2)){
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                    etPwd1.setText("");
                    etPwd2.setText("");
                    etPwd1.requestFocus();
                    return;
                }
                SQLiteUserHelper sqLiteUserHelper = new SQLiteUserHelper(getApplicationContext());
                SQLiteDatabase sqLiteDatabase = sqLiteUserHelper.getReadableDatabase();
                int number=0;
                Cursor cursor = sqLiteDatabase.rawQuery("select * from user", null);
                number=cursor.getCount();
                if (number>0){
                    cursor.close();
                    sqLiteDatabase.close();
                    sqLiteUserHelper.close();
                    Toast.makeText(RegisterActivity.this, "对不起，该APP只能注册一次,请登录！！！", Toast.LENGTH_SHORT).show();
                    Intent intentLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intentLogin);
                    return;
                }
                cursor.close();
                sqLiteDatabase.close();
                sqLiteUserHelper.close();
                sqLiteDatabase = sqLiteUserHelper.getWritableDatabase();
                try {
                    sqLiteDatabase.execSQL("insert into user(phone,card,pwd) values(?,?,?)", new String[]{phone, card, pwd1});
                    //返回结果
                    Intent intent = new Intent();
                    intent.putExtra("phone", phone);
                    intent.putExtra("card",card);
                    setResult(RESULT_OK, intent);
                    finish();
                }catch (Exception e){

                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                }finally {
                    sqLiteDatabase.close();
                    sqLiteUserHelper.close();
                }
            }
        });
        //返回登录界面
        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
