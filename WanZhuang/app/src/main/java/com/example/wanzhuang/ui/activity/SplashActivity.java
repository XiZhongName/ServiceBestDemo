package com.example.wanzhuang.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.wanzhuang.R;

public class SplashActivity extends AppCompatActivity {
    private  static int SPLASH_DISPLAY_LENGTH = 3000;//延迟时间
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();//关闭SplashActivity，将其回收，否则按返回键会返回此界面
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}