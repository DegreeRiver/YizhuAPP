package com.dujiang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dujiang.myapplication.dao.FileDao;
import com.dujiang.myapplication.vo.Phone;

public class AddParentActivity extends AppCompatActivity {

    private EditText etPhone,etName;
    private Phone phone ;
    private Button btnSave,btnCancel;
    private FileDao fileDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent);

        phone = new Phone();
        fileDao = new FileDao(this);

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
                    phone.setName(etName.getText().toString());
                    if (fileDao.addPhone(phone)<0){
                        Toast.makeText(AddParentActivity.this,"没有成功添加...",Toast.LENGTH_LONG).show();
                    }else {
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

        return ;

    }
}
