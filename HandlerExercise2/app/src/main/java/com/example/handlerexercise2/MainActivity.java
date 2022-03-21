package com.example.handlerexercise2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView,textView2;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    textView.setText("执行线程1的操作");
                    break;
                case 2:
                    textView2.setText("执行线程2的操作");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        textView2 = findViewById(R.id.textView);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "线程1的数据";
                mHandler.sendMessage(message);
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = "这是线程2的数据";
                mHandler.sendMessage(msg);
            }
        }).start();
    }

}


















