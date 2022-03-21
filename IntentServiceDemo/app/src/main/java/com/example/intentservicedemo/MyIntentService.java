package com.example.intentservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {



    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String num = (String) intent.getExtras().get("num");
            if ("1".endsWith(num)){
                Log.i("tag", Thread.currentThread().getName() + "**" + num);
                for (int i = 1; i <11; i++) {

                    try {
                        Thread.sleep(1000);
                        Log.i("tag", Thread.currentThread().getName() + "**" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if ("2".endsWith(num)){
                Log.i("tag", Thread.currentThread().getName() + "--" + num);
                for (int i = 11; i < 21; i++) {
                    Log.i("tag", Thread.currentThread().getName() + "--" + i);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag", "服务销毁");
    }
}