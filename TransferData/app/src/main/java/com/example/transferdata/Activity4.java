package com.example.transferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle people1 = intent.getBundleExtra("people1");
            Bundle people2 = intent.getBundleExtra("people2");
            String name1 = people1.getString("userName");
            String name2 = people2.getString("userName");
            Toast.makeText(Activity4.this, "这是传递的数据：" + name1 + "," + name2, Toast.LENGTH_LONG).show();
        }

    }
}