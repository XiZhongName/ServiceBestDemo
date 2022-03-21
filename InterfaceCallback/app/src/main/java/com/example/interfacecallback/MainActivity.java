package com.example.interfacecallback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data data = new Data(this);
        data.sends();
    }

    @Override
    public void send(String s) {
        Log.e("tag", s);
    }
}























