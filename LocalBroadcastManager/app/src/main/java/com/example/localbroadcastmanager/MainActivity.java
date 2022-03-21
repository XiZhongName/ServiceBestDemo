package com.example.localbroadcastmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
         button = findViewById(R.id.button);
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent("com.example.localbroadcastmanager");
                 localBroadcastManager.sendBroadcast(intent);//发送本地广播
             }
         });
            intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.localbroadcastmanager");
            localReceiver = new LocalReceiver();
            localBroadcastManager.registerReceiver(localReceiver,intentFilter);//注册本地广播监听器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}















