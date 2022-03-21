package com.example.handlerexercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView textView1, textView2;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        mHandler = new Handler();
        Log.e("tag", Thread.currentThread().getId() + "主线程");
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Log.e("tag", Thread.currentThread().getId() + "子线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setText("这是线程1的data");
                        Log.e("tag", Thread.currentThread().getId() + "post");
                    }
                });
            }
        }.start();






        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText("这是线程2的data");
                    }
                });
            }
        }.start();
    }
}