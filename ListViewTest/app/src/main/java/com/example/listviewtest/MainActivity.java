package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String data[] = {"apple","banana","orange","watermelon","pear","grape","mango",
            "apple","banana","orange","watermelon","pear","grape","mango"};
    private List<Fruit>fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("apple",R.drawable.img_apple);
            fruitList.add(apple);
            Fruit banana = new Fruit("banana",R.drawable.img_banana);
            fruitList.add(banana);
            Fruit orange = new Fruit("orange",R.drawable.img_orange);
            fruitList.add(orange);
            Fruit mango = new Fruit("mango",R.drawable.img_mango);
            fruitList.add(mango);
            Fruit grape = new Fruit("grape",R.drawable.img_grape);
            fruitList.add(grape);
            Fruit pear = new Fruit("pear",R.drawable.img_tao);
            fruitList.add(pear);
        }
    }
}