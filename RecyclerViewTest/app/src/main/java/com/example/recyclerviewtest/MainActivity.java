package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    List<News> mNewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        for (int i = 0; i < 30; i++) {
            News news = new News();
            news.setTitle("标题" + i);
            news.setContent("内容" + i);
            mNewList.add(news);
        }
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    class MyViewHoder extends RecyclerView.ViewHolder{
        TextView mTitleTv;
        TextView mTitleContnet;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.textView);
            mTitleContnet = itemView.findViewById(R.id.textView2);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHoder>{

        @NonNull
        @Override//创建view
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(MainActivity.this,R.layout.item_list,null);
            MyViewHoder myViewHoder = new MyViewHoder(view);
            return myViewHoder;
        }

        @Override//绑定数据
        public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            News news = mNewList.get(position);
            holder.mTitleTv.setText(news.getTitle());
            holder.mTitleContnet.setText(news.getContent());
        }

        @Override//获取条数
        public int getItemCount() {
            return mNewList.size();
        }
    }
}