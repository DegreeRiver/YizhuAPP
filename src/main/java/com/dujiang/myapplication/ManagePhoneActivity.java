package com.dujiang.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dujiang.myapplication.dao.FileDao;

public class ManagePhoneActivity extends AppCompatActivity {

    private EditText etId,etName,etPhone;
    private Button btnDelete,btnUpdate,btnCancle;
    private FileDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_phone);

        //获取控件
        etName = (EditText) findViewById(R.id.et_manage_name1);
        etId = (EditText) findViewById(R.id.et_manage_id1);
        etPhone = (EditText) findViewById(R.id.et_manage_phone1);

        dao = new FileDao(this);

        btnCancle = (Button) findViewById(R.id.btn_manage_cancel1);
        btnDelete = (Button) findViewById(R.id.btn_manage_delete1);
        btnUpdate = (Button) findViewById(R.id.btn_manage_update1);

        Intent intent = getIntent();
        etId.setId(intent.getIntExtra("id",1));
        etName.setText(intent.getStringExtra("name"));
        etPhone.setText(intent.getStringExtra("phone"));

        //点击取消按钮
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //创建一个对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagePhoneActivity.this);
                builder.setTitle("删除手机号");
                builder.setMessage("你确定要删除这个亲友的手机吗?");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dao.phoneDelete(Integer.valueOf(etId.getId())) > 0) {
                            Toast.makeText(ManagePhoneActivity.this, "删除成功！！！", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(ManagePhoneActivity.this, MainActivity.class);
                            startActivity(intent2);
                        } else {
                            Toast.makeText(ManagePhoneActivity.this, "删除失败！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.create().show();
            }
        });
           btnUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                 if (dao.phoneUpdate(etId.getId(),
                         etName.getText().toString(),
                         etPhone.getText().toString())>0){
                     Toast.makeText(ManagePhoneActivity.this, "修改成功！！！", Toast.LENGTH_SHORT).show();
                     Intent intent1 = new Intent(ManagePhoneActivity.this, MainActivity.class);
                     startActivity(intent1);
                 }else {
                     Toast.makeText(ManagePhoneActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                 }
               }
           });

    }
}
