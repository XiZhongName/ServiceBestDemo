package com.example.servicelifecycledemo2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("tag", "onCreate.... ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("tag", "onStartCommand.... ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag", "onDestroy.... ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("tag", "onUnbind.... ");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("tag", "onRebind.... ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("tag", "onBind.... ");
        return null;

    }
}