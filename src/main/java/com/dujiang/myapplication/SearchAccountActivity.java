package com.dujiang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dujiang.myapplication.dao.FileDao;
import com.dujiang.myapplication.vo.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAccountActivity extends AppCompatActivity {

    private ListView lvAcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_account);

        lvAcount = (ListView) findViewById(R.id.lv_my_account);
        FileDao dao = new FileDao(this);
        List<Account> list0 = dao.myAccount();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        final SimpleAdapter adapter;
        for (Account account : list0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", account.getId());
            map.put("type", account.getType());
            map.put("userid", account.getUserId());
            map.put("pwd", account.getPwd());
            map.put("other", account.getOther());
            list.add(map);
        }
        adapter = new SimpleAdapter(this, list, R.layout.listview_search,
                new String[]{"id", "type", "userid", "pwd", "other"},
                new int[]{R.id.tv_listview_id, R.id.tv_listview_type, R.id.tv_listview_userid, R.id.tv_listview_pwd, R.id.tv_listview_other});

        lvAcount.setAdapter(adapter);
        lvAcount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id1) {
                Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);
                Integer id = Integer.valueOf(map.get("id").toString());
                String type = map.get("type").toString();
                String userid = map.get("userid").toString();
                String pwd = map.get("pwd").toString();
                String other = map.get("other").toString();

                Intent intent = new Intent(SearchAccountActivity.this, ManageActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                intent.putExtra("userid", userid);
                intent.putExtra("pwd", pwd);
                intent.putExtra("other", other);


                startActivity(intent);
            }
        });
    }
}
