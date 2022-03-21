package com.example.notificationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelId = "default";
                    String channelName = "默认通知";
                    //new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH));
                }
                Notification notification = new NotificationCompat.Builder(MainActivity.this, "default")
                        .setContentTitle("今日热点")
                       // .setContentText("立陶宛外交部长宣布辞职")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pi)
                        .setSound(Uri.fromFile(new File("system/media/audio/ringtones/")))//设置通知铃声
                        .setVibrate(new long[]{0,1000,1000,1000})//设置通知震动
                        .setLights(Color.GREEN,1000,1000)//设置呼吸灯
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("窗外的麻雀在电线杆上多嘴，你说这一句很有夏天的感觉" +
                                "，手中的铅笔在纸上来来回回，秋刀鱼的滋味，猫和你都想了解，初恋的香味就这样被我们寻回。"))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true)//点击通知之后自动取消
                        .build();
                notificationManager.notify(1, notification);
            }
        });
    }


}