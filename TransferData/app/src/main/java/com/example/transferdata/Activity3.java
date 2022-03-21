package com.example.transferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Intent intent = getIntent();

        if (intent != null){
            Bundle bundle = intent.getExtras();
            String name = bundle.getString("userName");
            int age = bundle.getInt("age");
            char sex = bundle.getChar("sex");
            Toast.makeText(Activity3.this, "这是传递过来的数据："
                    + name + age + sex, Toast.LENGTH_LONG).show();
        }


    }
}