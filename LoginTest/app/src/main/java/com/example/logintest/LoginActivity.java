package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.tv_content);
        //取回登录的账号信息
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        textView.setText("欢迎你：" + account);

    }

    public void out(View view) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);

        startActivity(intent);
        this.finish();

    }
}