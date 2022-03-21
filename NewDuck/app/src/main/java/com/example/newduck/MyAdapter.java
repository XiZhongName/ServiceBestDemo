package com.example.newduck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<News> myNews ;
    private LayoutInflater mLayoutInflater;
    Context context;

    public MyAdapter(List<News> myNews, Context context) {
        this.context = context;
        this.myNews = myNews;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.news_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        News news = myNews.get(position);
    }

    @Override
    public int getItemCount() {
        return myNews.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


















