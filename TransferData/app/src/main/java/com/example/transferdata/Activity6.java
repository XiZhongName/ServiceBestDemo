package com.example.transferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        Intent intent = getIntent();

    }

    public void back(View view) {
        Intent intent = getIntent();
        intent.putExtra("result","本兮");
        setResult(1,intent);
        this.finish();
    }
}