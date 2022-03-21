package com.example.servicedemo;

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
    private static final String CHANNEL_ID = "com.example.servicedemo";
    private static final String CHANNEL_NAME = "TEST";
    private DownloadBinder mBinder = new DownloadBinder();
    private NotificationChannel notificationChannel;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tag", "onCreate: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, builder.build());//第一个参数是通知的id，第二个参数是构建出的Notification对象
        //调用startForeground方法就会让MyService变成一个前台服务
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("tag", "onStartCommand: 服务已经启动");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "onDestroy: 服务已销毁");
    }
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("tag", "startDownload: 开始下载...");
        }
        public int getProgress(){
            Log.d("tag", "startDownload: 下载...");
            return 0;
        }
    }
}

















