package com.dujiang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dujiang.myapplication.dao.FileDao;
import com.dujiang.myapplication.vo.Phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookPhoneActivity extends AppCompatActivity {

    private ListView lvMyPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_phone);

        lvMyPhone = (ListView) findViewById(R.id.lv_my_phone);

        FileDao dao = new FileDao(this);
        List<Phone> list0 = dao.myPhone();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        final SimpleAdapter adapter ;
        for (Phone phone :list0){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",phone.getId());
            map.put("name",phone.getName());
            map.put("phone",phone.getPhone());
            list.add(map);
        }
        adapter = new SimpleAdapter(this,list,R.layout.listview_lookphone,
                new String[]{"id","name","phone"},
                new int[]{R.id.tv_look_id,R.id.tv_look_name,R.id.tv_look_phone});

        lvMyPhone.setAdapter(adapter);
        lvMyPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id1) {

                Map<String,Object> map = (Map<String, Object>) adapter.getItem(position);

                Integer id = Integer.valueOf(map.get("id").toString());
                String name = map.get("name").toString();
                String phone = map.get("phone").toString();

                Intent intent = new Intent(LookPhoneActivity.this,ManagePhoneActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
    }
}
