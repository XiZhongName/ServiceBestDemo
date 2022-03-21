package com.example.handlerexercise32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MainActivity3 extends AppCompatActivity {
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.e("tag", Thread.currentThread().getId() + "主线程");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        start();
                    }
                });
                Looper.loop();
            }
        }).start();
    }

    private void start() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Log.e("tag", Thread.currentThread().getId() + "主线程handler");
            }
        });
        if (mHandler != null){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("tag", Thread.currentThread().getId() + "子线程handler");
                }
            });
        }
    }
}












