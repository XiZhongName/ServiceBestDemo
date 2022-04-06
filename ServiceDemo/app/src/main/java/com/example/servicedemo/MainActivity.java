package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_start,btn_end,btn_bind,btn_unBind;
    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        //与服务绑定成功时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;//向下转型
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        //与服务解绑时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//绑定ButterKnife注解
        btn_start = findViewById(R.id.btn_start);
        btn_end = findViewById(R.id.btn_end);
        btn_bind = findViewById(R.id.btn_bind);
        btn_unBind = findViewById(R.id.btn_unBind);
        btn_start.setOnClickListener(this);
        btn_end.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
        btn_unBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                Intent intent = new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_end:
                Intent intent2 = new Intent(this,MyService.class);
                stopService(intent2);
                break;
            case R.id.btn_bind:
                Intent intent3 = new Intent(this,MyService.class);
                bindService(intent3,connection,BIND_AUTO_CREATE);//绑定服务，第三个参数表示活动和服务进行绑定后自动创建服务
                break;
            case R.id.btn_unBind:
                unbindService(connection);//解绑服务
                break;
            default:
                break;
        }
    }
}














