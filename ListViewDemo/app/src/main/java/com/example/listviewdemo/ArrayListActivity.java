package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ArrayListActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mStringList;
    private ArrayAdapter<String> mArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);
        mListView = findViewById(R.id.lv);
        mStringList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mStringList.add("这是条目" + i);
        }
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,mStringList);
        mListView.setAdapter(mArrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ArrayListActivity.this, "你点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ArrayListActivity.this, "你长按了"+position, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }
}