package com.dujiang.myapplication;

import android.animation.Animator;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.dujiang.myapplication.util.ParticleView;
import com.dujiang.myapplication.util.SQLiteUserHelper;
import com.dujiang.myapplication.vo.User;

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegister, tvParents;

    private ImageView ivClear, iv1, iv2, iv3;

    private EditText etPhone, etCard, etPwd;

    private Button btnLogin;

    private ToggleButton tbPasswordVisibility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ParticleView particleAnimator = new ParticleView(LoginActivity.this, 2000);
        particleAnimator.setOnAnimationListener(new ParticleView.OnAnimationListener() {
            @Override
            public void onAnimationStart(View view, Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(View view, Animator animation) {

            }
        });


        //获取组件
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleAnimator.boom(view);

            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleAnimator.boom(view);

            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleAnimator.boom(view);
            }
        });
        ivClear = (ImageView) findViewById(R.id.iv_clear);
        tvParents = (TextView) findViewById(R.id.login_tv_parents);
        tvRegister = (TextView) findViewById(R.id.login_tv_register);
        etPhone = (EditText) findViewById(R.id.login_et_phone);
        etCard = (EditText) findViewById(R.id.login_et_card);
        etPwd = (EditText) findViewById(R.id.login_et_pwd);
        btnLogin = (Button) findViewById(R.id.login_btn);
        tbPasswordVisibility = (ToggleButton) findViewById(R.id.tb_password_visibility);

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                particleAnimator.boom(view);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //particleAnimator.boom(view);
                //getWindow().getDecorView().findViewById(android.R.id.background);
                switch (view.getId()) {
                    case R.id.login_btn:
                        String phone = etPhone.getText().toString();
                        String card = etCard.getText().toString();
                        String pwd = etPwd.getText().toString();

                        //做查询数据库的操作

                        SQLiteUserHelper sqLiteUserHelper = new SQLiteUserHelper(getApplicationContext());
                        SQLiteDatabase sqLiteDatabase = sqLiteUserHelper.getReadableDatabase();
                        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where phone=? and card=? and pwd=?", new String[]{phone, card, pwd});
                        User user = null;
                        if (cursor.moveToNext()) {
                            user = new User();
                            user.setUserid(cursor.getInt(0));
                            user.setPhone(cursor.getString(1));
                            user.setCard(cursor.getString(2));
                            user.setPwd(cursor.getString(3));
                        }
                        cursor.close();
                        sqLiteDatabase.close();
                        sqLiteUserHelper.close();

                        if (user != null) {
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            //final Handler handler = new Handler();
                            //handler.postDelayed(runnable,2000);
                        } else {
                            Toast.makeText(LoginActivity.this, "用户输入错误", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册界面
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                //以返回结果的方式启动注册界面
                startActivityForResult(intentRegister, 1);
            }
        });
        tvParents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentParents = new Intent(LoginActivity.this, ParentsLoginActivity.class);
                startActivity(intentParents);
            }
        });
        tbPasswordVisibility.setOnCheckedChangeListener(new ToggleButtonClick());

    }

    //3、密码可见性按钮监听
    private class ToggleButtonClick implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            //5、判断事件源的选中状态
            if (isChecked) {

                //显示密码
                //etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                // 隐藏密码
                //etPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
            //6、每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
            etPwd.setSelection(etPwd.length());
        }
    }

    //接收从Register传递过来的Phone和Card
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 判断是哪个返回
        if (requestCode == 1) {
            // 判断返回的结果码
            if (resultCode == RESULT_OK) {
                Toast.makeText(LoginActivity.this, "恭喜，注册成功啦！", Toast.LENGTH_SHORT).show();
                String phone = data.getStringExtra("phone");
                String card = data.getStringExtra("card");
                etPhone.setText(phone);
                etCard.setText(card);
                etPwd.requestFocus();
            }
        }
    }


}
