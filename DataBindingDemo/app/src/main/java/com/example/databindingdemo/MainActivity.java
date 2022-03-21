package com.example.databindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.example.databindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Ido ido = new Ido("徐东东","四星");
        ido.setName("李冰冰");
        mainBinding.setIdo(ido);
        mainBinding.setEventHandler(new EventHandlerListener(this));
    }
}