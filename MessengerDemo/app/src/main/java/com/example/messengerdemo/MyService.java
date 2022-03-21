package com.example.messengerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    final static String TAG = "msg";
    final static int MSG_SAY_HELLO = 1;

    public MyService() {
    }


    Messenger messenger = new Messenger(new MyHandler());

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "绑定成功");
        return messenger.getBinder();
    }

    public class MyHandler extends Handler {
        public void handlerMessage(Message message) {
            //接收客户端发来的消息
            switch (message.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello" + message.getData().getString("data"), Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(message);
        }
    }
}