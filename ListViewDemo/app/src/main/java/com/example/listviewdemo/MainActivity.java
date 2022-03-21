package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toArrayListTest(View view) {
        Intent intent = new Intent(this,ArrayListActivity.class);
        startActivity(intent);
    }

    public void toSimple(View view) {
        Intent intent = new Intent(this,SimpleListActivity.class);
        startActivity(intent);
    }

    public void toBaseAdapter(View view) {
        Intent intent = new Intent(this,BaseAdapterActivity.class);
        startActivity(intent);
    }
}