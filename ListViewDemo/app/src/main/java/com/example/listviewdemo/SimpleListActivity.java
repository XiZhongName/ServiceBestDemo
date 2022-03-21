package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleListActivity extends AppCompatActivity {
    private ListView mListView;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, Object>> mList;
    private int[] images = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
        mListView = findViewById(R.id.lv);
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String, Object> map = new HashMap();
            map.put("img", images[i%images.length]);
            map.put("title", "这是标题" + i);
            map.put("content", "这是内容" + i);
            mList.add(map);
        }

        mSimpleAdapter = new SimpleAdapter(this,
                mList,
                R.layout.list_item_layout,
                new String[]{"img","title","content"},
                new int[]{R.id.iv_img,R.id.tv_title,R.id.tv_content}
                );
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = mList.get(position);
                String title = (String)map.get("title");
                Toast.makeText(SimpleListActivity.this, "你点击了"+position, Toast.LENGTH_SHORT).show();

            }
        });

    }
}