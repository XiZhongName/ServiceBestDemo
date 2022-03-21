package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    private String CHANNEL_ID = "com.example.servicetest";
    NotificationChannel notificationChannel = null;
    String CHANNEL_NAME = "TEST";

    public MyService() {
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("tag","开始下载");
        }
        public int getProgress(){
            Log.d("tag","获取进度");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tag","服务运行");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,CHANNEL_ID);
                notification.setContentTitle("这是内容标题");
                notification.setContentText("这是内容");
                notification.setWhen(System.currentTimeMillis());
                notification.setSmallIcon(R.mipmap.ic_launcher);
                notification.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                notification.setContentIntent(pi);
        startForeground(1,notification.build());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("tag","服务已开始运行");
        new Thread(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag","服务已销毁");
    }
}