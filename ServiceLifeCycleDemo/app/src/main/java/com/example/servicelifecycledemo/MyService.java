package com.example.servicelifecycledemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer player1;
    MediaPlayer player2;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("TAG", "onBind在运行... ");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "创建后台服务", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onCreate在运行... ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand在运行... ");
        if (intent.getExtras().get("music").equals("R.raw.qing")){
            player1 = MediaPlayer.create(this,R.raw.qing);
            player1.start();
        } else {
            player2 = MediaPlayer.create(this,R.raw.ting);
            player2.start();
        }
        Toast.makeText(this, "服务开始运行", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
       if (player1 != null){
           player1.release();
           player1 = null;
       }
        if (player2 != null){
            player2.release();
            player2 = null;
        }
        Toast.makeText(this, "服务销毁", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onDestroy执行... ");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("TAG", "onUnbind在运行... ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("TAG", "onRebind在运行... ");
        super.onRebind(intent);
    }
}