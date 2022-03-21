package com.example.handlerpostdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "tag";
    private TextView textView;
    private int count = 0;
    private ProgressBar progressBar;

    Handler handler = new Handler();
    /*线程1，提示*/
    Runnable mRunToast = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, "不要抽烟了", Toast.LENGTH_LONG).show();
        }
    };
    /*线程2，计数*/
    Runnable mRunCount = new Runnable() {
        @Override
        public void run() {
            textView.setText("count:" + String.valueOf(++count));
            handler.postDelayed(mRunCount, 1000);//延迟处理
        }
    };
    /*创建Handler对象 消息处理对象*/
    Handler updateProgressBarHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "消息内容为: " + msg.arg1);
            progressBar.setProgress(msg.arg1);
            updateProgressBarHandler.post(mUpdateProgressBarThread);
            if (msg.arg1 >= 100){
                updateProgressBarHandler.removeCallbacks(mUpdateProgressBarThread);
            }

        }
    };
    /*线程3. 在日志中显示信息，并增加arg1*/
    Runnable mUpdateProgressBarThread = new Runnable() {
        int i = 0;
        @Override
        public void run() {
            i = i + 10;
            Message msg = updateProgressBarHandler.obtainMessage();//获得Message对象
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgressBarHandler.sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.start_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.toast_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.update_progressbar_btn)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_progressbar_btn)).setOnClickListener(this);
        textView = findViewById(R.id.tv_text);
        progressBar = findViewById(R.id.pgb_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                handler.postDelayed(mRunCount, 1000);//每个一秒执行一次mRunCount
                break;
            case R.id.stop_btn:
                handler.removeCallbacks(mRunCount);//把当前对象移出去，结束线程
                break;
            case R.id.toast_btn:
                handler.postAtTime(mRunToast, SystemClock.uptimeMillis() + 5 * 1000);
                break;
            case R.id.update_progressbar_btn:
                updateProgressBarHandler.post(mUpdateProgressBarThread);
                break;
            case R.id.stop_progressbar_btn:
                updateProgressBarHandler.removeCallbacks(mUpdateProgressBarThread);
                break;
        }
    }
}