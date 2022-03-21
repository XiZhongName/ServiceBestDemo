package com.example.messengerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Boolean mBind = false;
    Messenger rMessenger = null;

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("msg", "连接成功");
            rMessenger = new Messenger(service);
            mBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("msg", "连接断开");
            mBind = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHello(View view) {
        if (!mBind) return;
        Message msg = Message.obtain(null,MyService.MSG_SAY_HELLO);
        Bundle bundle = new Bundle();
        bundle.putString("data","今天不开心吗？");
        msg.setData(bundle);
        //客户端向服务端发送消息
        try {
            rMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBind){
            unbindService(mConnection);
            mBind = false;
        }
    }
}

























