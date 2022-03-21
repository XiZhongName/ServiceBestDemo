package com.example.handlerexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String str = (String) msg.obj;
                textView.setText(str);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                begin();
                Toast.makeText(MainActivity.this, "主线程任务!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void begin(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String string = getString();
                Message message = Message.obtain();
                message.what = 0;
                message.obj = string;
                handler.sendMessage(message);
            }
        }).start();

    }
    private String getString(){
        String str = "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            builder.append("七里香" + i);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        str = builder.toString();
        return str;
    }
}






















