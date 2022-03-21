package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit>fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
    private void initFruits(){
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("apple"),R.drawable.img_apple);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("banana"),R.drawable.img_banana);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("orange"),R.drawable.img_orange);
            fruitList.add(orange);
            Fruit mango = new Fruit(getRandomLengthName("mango"),R.drawable.img_mango);
            fruitList.add(mango);
            Fruit grape = new Fruit(getRandomLengthName("grape"),R.drawable.img_grape);
            fruitList.add(grape);
            Fruit pear = new Fruit(getRandomLengthName("pear"),R.drawable.img_tao);
            fruitList.add(pear);
            Fruit gua = new Fruit(getRandomLengthName("xigua"),R.drawable.img_gua);
            fruitList.add(gua);
            Fruit li = new Fruit(getRandomLengthName("li"),R.drawable.img_li);
            fruitList.add(li);
            Fruit lizhi = new Fruit(getRandomLengthName("lizhi"),R.drawable.img_lizhi);
            fruitList.add(lizhi);
        }
    }
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}