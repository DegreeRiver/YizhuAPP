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

public class ManageActivity extends AppCompatActivity {

    private EditText etId,etType,etUserId,etPwd,etOther;
    private Button btnDelete,btnUpdate,btnCancel;
    private FileDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        //获取控件
        etId = (EditText) findViewById(R.id.et_manage_id);
        etType = (EditText) findViewById(R.id.et_manage_type);
        etUserId = (EditText) findViewById(R.id.et_manage_userid);
        etPwd = (EditText) findViewById(R.id.et_manage_pwd);
        etOther = (EditText) findViewById(R.id.et_manage_other);

        dao = new FileDao(this);

        btnCancel = (Button) findViewById(R.id.btn_manage_cancel);
        btnDelete = (Button) findViewById(R.id.btn_manage_delete);
        btnUpdate = (Button) findViewById(R.id.btn_manage_update);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //接收从SearchActivity传递过来的值
        final Intent intent = getIntent();
        etId.setId(intent.getIntExtra("id",1));
        etType.setText(intent.getStringExtra("type"));
        etUserId.setText(intent.getStringExtra("userid"));
        etPwd.setText(intent.getStringExtra("pwd"));
        etOther.setText(intent.getStringExtra("other"));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除需要创建一个对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageActivity.this);
                builder.setTitle("删除！！！");
                builder.setMessage("你确定要删除“ "+etType.getText()+" ”吗?");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (dao.accountDelete(etId.getId())>0){
                            Toast.makeText(ManageActivity.this, "删除成功！！！", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(ManageActivity.this, MainActivity.class);
                            startActivity(intent2);
                            finish();
                        }else {
                            Toast.makeText(ManageActivity.this, "删除失败！！！", Toast.LENGTH_SHORT).show();

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
                if (dao.accountUpadate(etId.getId(),
                        etType.getText().toString(),
                        etUserId.getText().toString(),
                        etPwd.getText().toString(),
                        etOther.getText().toString())>0){
                    Toast.makeText(ManageActivity.this, "修改成功！！！", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(ManageActivity.this, MainActivity.class);
                    startActivity(intent1);
                    finish();
                }else {
                    Toast.makeText(ManageActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
