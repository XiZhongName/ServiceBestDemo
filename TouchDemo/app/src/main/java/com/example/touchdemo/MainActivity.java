package com.example.touchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    TestView testView;
    VelocityTracker velocityTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = findViewById(R.id.testview);
        testView.setOnTouchListener(new mOnTouch());
    }
    private class mOnTouch implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x1,y1;
            x1 = (int) event.getX();
            y1 = (int) event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    testView.setXY(x1,y1);
                    testView.invalidate();//页面刷新
                    if (velocityTracker == null){
                        velocityTracker = VelocityTracker.obtain();//初始化
                    } else {
                        velocityTracker.clear();
                    }
                    velocityTracker.addMovement(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    testView.setXY(x1,y1);
                    testView.invalidate();
                    velocityTracker.addMovement(event);
                    velocityTracker.computeCurrentVelocity(1000);
                    Log.e("tag", "x的速率: " + velocityTracker.getXVelocity() + "像素/秒, + y的速率:" + velocityTracker.getYVelocity() + "像素/秒");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
                    if (velocityTracker != null){
                        velocityTracker.recycle();
                        velocityTracker = null;
                    }
                    break;
            }
            return true;
        }
    }
}
























