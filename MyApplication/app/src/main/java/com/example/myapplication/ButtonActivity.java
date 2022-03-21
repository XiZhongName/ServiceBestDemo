package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button btn1 = findViewById(R.id.btn1);
        MyClickListener mcl = new MyClickListener();
        btn1.setOnClickListener(mcl);

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "通过匿名内部类实现按钮");
            }
        });
        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("tsg", "通过Activity本类实现按钮");
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.e("tag", "刚点击了按钮通过内部类实现了点击事件");
        }
    }

    public void myClick(View view) {//参数：被点击的控件对象
        switch (view.getId()) {
            case R.id.btn4:
                Log.e("tsg", "通过xml绑定实现按钮btn4");
                Toast.makeText(this,"hhhhhhhhh",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn5:
                Log.e("tsg", "通过xml绑定实现按钮btn5");
                Toast.makeText(this,"pppppppp",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}