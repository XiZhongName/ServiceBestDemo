package com.example.firstandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容视图
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MainActivity.this, Class.forName("com.example.firstandriod.ButtonActivity")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}