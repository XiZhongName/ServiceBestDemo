package com.example.recyclerviewtest3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    List<Fruit> mFruit = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        RefreshLayout refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                for (int i = 0; i < 10; i++) {
                    Fruit fruit = new Fruit();
                    fruit.setName("新水果"+ i);
                    fruit.setColor("新颜色" + i);
                    mFruit.add(fruit);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                for (int i = 0; i < 10; i++) {
                    Fruit fruit = new Fruit();
                    fruit.setName("新新水果" + i);
                    fruit.setColor("新新颜色" + i);
                    mFruit.add(fruit);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        for (int i = 0; i < 20; i++) {
            Fruit fruit = new Fruit();
            fruit.setName("苹果");
            fruit.setColor("红色");
            mFruit.add(fruit);
        }
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView item_name;
        TextView item_color;
        LinearLayout mRootView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_color = itemView.findViewById(R.id.item_color);
            mRootView = findViewById(R.id.root_view);
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
            Fruit fruit = mFruit.get(position);
            holder.item_name.setText(fruit.getName());
            holder.item_color.setText(fruit.getColor());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"你点击了" ,Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mFruit.size();
        }
    }
}