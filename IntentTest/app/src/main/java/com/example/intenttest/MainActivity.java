package com.example.intenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpActivity(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.intenttest.CustomActivity");
        startActivity(intent);
    }

    public void toBaidu(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }
}