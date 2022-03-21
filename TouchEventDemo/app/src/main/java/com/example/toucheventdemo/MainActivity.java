package com.example.toucheventdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, View.OnLongClickListener {
    TestView testView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = findViewById(R.id.testview);
        testView.setOnClickListener(this);
        testView.setOnTouchListener(this);
        testView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "onLongClick", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "onTouch" + event.getAction(), Toast.LENGTH_SHORT).show();
        return true;
    }
}