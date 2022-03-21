package com.example.databindingexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databindingexercise.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private User user;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User();
        user.setName("张三");
        user.setPassword("123");
        mainBinding.setUserInfo(user);
    }
}