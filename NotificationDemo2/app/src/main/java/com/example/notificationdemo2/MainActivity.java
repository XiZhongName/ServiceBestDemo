package com.example.notificationdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button sendNotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendNotify = findViewById(R.id.send_notify);
    }

    public void sendNotification(View view) {
        //1.获得一个通知管理器
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //2.建立一个通知   8.0后需要自建通道
        Notification notification = null;
        String id = "mChannel";
        String name = "通道1";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {//判断版本
            //自建通道
            NotificationChannel mChannel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);//把通道和通道管理器相关联
            //真正的建立一个通知
            notification = new Notification.Builder(this,id)
                    //设置大图标
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_baseline_event_note_24))
                    //设置小图标
                    .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.img3)))
                    .setContentTitle("好消息")
                    .setContentText("下周不上课")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    //3.绑定对应的Activity
                    .setContentIntent(PendingIntent.getActivity(this,1,new Intent(this,NotificationResult.class),PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();
        } else {
            notification = new Notification.Builder(this)
                    .setContentTitle("好消息")
                    .setContentText("下周不上课")
                    .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                    .setOngoing(true)
                    .setContentIntent(PendingIntent.getActivity(this, 1, new Intent(this, NotificationResult.class), PendingIntent.FLAG_CANCEL_CURRENT))
                    .build();
        }
        //4.发出通知
        notificationManager.notify(0,notification);
    }
}
















