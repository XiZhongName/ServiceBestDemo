package com.example.multiactiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mActionPointerId;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mActionPointerId = event.getPointerId(0);//得到当前触摸点
        int pointerIndex = event.findPointerIndex(mActionPointerId);//当前触摸点索引
        float x = event.getX(pointerIndex);//当前触摸点坐标
        float y = event.getY(pointerIndex);
        if (event.getPointerCount() > 1){//大于1是多点触控
            for (int i = 0; i < event.getPointerCount(); i++) {
                pointerIndex = event.findPointerIndex(i);
                x = event.getX(pointerIndex);
                y = event.getY(pointerIndex);
                Log.d("tag", "多点触控第" + i + "个坐标:" + x + "," + y);
                
            }
        } else {
            Log.d("tag", "单点触控,坐标为: " + x +"," + y);
        }
        return true;

    }
}



























