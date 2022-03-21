package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewdemo.adapter.MyAdapter;
import com.example.listviewdemo.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterActivity extends AppCompatActivity {
    private ListView mListView;
    private List<ItemBean> mBeanList;
    private MyAdapter mMyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mMyAdapter = new MyAdapter(this,mBeanList);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BaseAdapterActivity.this,"你点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mBeanList = new ArrayList<>();
        ItemBean itemBean = new ItemBean();
        itemBean.setTitle("雨中漫步");
        itemBean.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean.setImgResId(R.drawable.img6);
        ItemBean itemBean2 = new ItemBean();
        itemBean2.setTitle("雨中漫步");
        itemBean2.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean2.setImgResId(R.drawable.img6);
        ItemBean itemBean3 = new ItemBean();
        itemBean3.setTitle("雨中漫步");
        itemBean3.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean3.setImgResId(R.drawable.img6);
        ItemBean itemBean4 = new ItemBean();
        itemBean4.setTitle("雨中漫步");
        itemBean4.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean4.setImgResId(R.drawable.img6);
        ItemBean itemBean5 = new ItemBean();
        itemBean5.setTitle("雨中漫步");
        itemBean5.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean5.setImgResId(R.drawable.img6);
        mBeanList.add(itemBean);
        mBeanList.add(itemBean4);
        mBeanList.add(itemBean2);
        mBeanList.add(itemBean3);
        mBeanList.add(itemBean5);
        mBeanList.add(itemBean);
        mBeanList.add(itemBean4);
        mBeanList.add(itemBean2);
        mBeanList.add(itemBean3);
        mBeanList.add(itemBean5);
        mBeanList.add(itemBean);
        mBeanList.add(itemBean4);
        mBeanList.add(itemBean2);
        mBeanList.add(itemBean3);
        mBeanList.add(itemBean5);
        mBeanList.add(itemBean);
        mBeanList.add(itemBean4);
        mBeanList.add(itemBean2);
        mBeanList.add(itemBean3);
        mBeanList.add(itemBean5);
    }

    private void initView() {
        mListView = findViewById(R.id.lv);
    }
}