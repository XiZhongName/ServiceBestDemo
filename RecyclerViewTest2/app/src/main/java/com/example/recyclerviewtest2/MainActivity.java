package com.example.recyclerviewtest2;

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

public class MainActivity extends AppCompatActivity{
    MyAdapter myAdapter;
    List<News> mNewsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTitle("新闻" + i );
            news.setContent("据新华社消息" + i);
            mNewsList.add(news);
        }
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView textTitle;
        TextView textContent;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.item_title);
            textContent = itemView.findViewById(R.id.item_content);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder>{


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(MainActivity.this,R.layout.items_list,null);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.textTitle.setText(news.getTitle());
        holder.textContent.setText(news.getContent());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }


}