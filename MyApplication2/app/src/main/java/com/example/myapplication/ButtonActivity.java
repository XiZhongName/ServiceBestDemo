package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button btn1 = findViewById(R.id.btn1);
        MyClickListener mcl = new MyClickListener();
        btn1.setOnClickListener(mcl);
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag","匿名内部类");
            }
        });
        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("tag","通过本类实现点击");
    }

    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.e("tag","通过内部类实现点击事件");
        }
    }
    //传入的参数：被点击的控件对象
    public void myClick(View v){
        switch(v.getId()){
            case R.id.btn4:
                Log.e("tag","通过xml绑定btn4实现点击事件");
                break;
            case R.id.btn5:
                Log.e("tag","通过xml绑定btn5实现点击事件");
                break;
        }

    }
}
