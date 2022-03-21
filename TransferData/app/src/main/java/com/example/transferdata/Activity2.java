package com.example.transferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("userName");
            int age = intent.getIntExtra("age", 18);
            boolean sing = intent.getBooleanExtra("sing", false);
            Toast.makeText(Activity2.this, "这是传递过来的："
                    + userName + age + sing, Toast.LENGTH_LONG).show();
        }

    }
}