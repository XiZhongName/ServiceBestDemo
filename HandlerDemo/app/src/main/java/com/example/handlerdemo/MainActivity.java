package com.example.handlerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            txt.setText("已修改了文本内容");
            Log.d("tag","这是handleMessage方法的" + Thread.currentThread().getId());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.tv_txt);
        new ActivityThread().start();
        Log.d("tag","这是onCreate方法的" + Thread.currentThread().getId());
    }
    class ActivityThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendMessage(new Message());
            Log.d("tag","这是ActivityThread方法的" + Thread.currentThread().getId());
        }
    }
}