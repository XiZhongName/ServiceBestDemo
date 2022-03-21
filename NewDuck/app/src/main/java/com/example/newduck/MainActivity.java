package com.example.newduck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<News> newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.title_layout);
        newsList = new ArrayList<>();
        News news = new News();
        news.setName("快要超神的提莫");
        news.setWork("HR");
        news.setContent("java急招300人，有无经验均可，参加培训后上岗");
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
        myAdapter = new MyAdapter(newsList,this);
        mRecyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}