package com.example.handlerdemo;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ButtonOnClick {
    private Context context;
    public void getStringContent(){
        try {
            Thread.sleep(5000);
            Toast.makeText(context, "hello,world", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
