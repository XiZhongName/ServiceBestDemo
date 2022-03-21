package com.example.databindingdemo;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class EventHandlerListener {
    private Context context;

    public EventHandlerListener(Context context) {
        this.context = context;
    }
    public void buttonOnclick(View view){
        Toast.makeText(context, "好喜欢", Toast.LENGTH_SHORT).show();
    }
}
