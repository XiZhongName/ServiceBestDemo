package com.example.recyclerviewtest4;

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
    RecyclerView mRecycler;
    MyAdapter myAdapter;
    List<Dog> myDog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.recycler_view);
        for (int i = 0; i < 30; i++) {
            Dog dog = new Dog();
            dog.setName("小黑" + i);
            dog.setAge("3" + i);
            myDog.add(dog);
        }
        MyAdapter myAdapter = new MyAdapter();
        mRecycler.setAdapter(myAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecycler.setLayoutManager(layoutManager);

    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        TextView itemAge;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemAge = itemView.findViewById(R.id.item_age);
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
           Dog dog = myDog.get(position);
           holder.itemName.setText(dog.getName());
           holder.itemAge.setText(dog.getAge());

        }

        @Override
        public int getItemCount() {
            return myDog.size();
        }
    }
}































