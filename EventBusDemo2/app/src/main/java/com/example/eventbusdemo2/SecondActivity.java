package com.example.eventbusdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.eventbusdemo2.databinding.ActivitySecondBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding secondBinding;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secondBinding = DataBindingUtil.setContentView(SecondActivity.this,R.layout.activity_second);
        button1 = findViewById(R.id.button2);
        EventBus.getDefault().register(SecondActivity.this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("你好，EventBus!"));
            }
        });
    }
//    public class ButtonClick{
//        public void buttonOnclick1(View view){
//            EventBus.getDefault().post(new MessageEvent("你好，EventBus!"));
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessage(MessageEvent event){
        Log.i("tag", "onMessage: " + event.getMessage());
        button1.setText(event.getMessage());
        EventBus.getDefault().removeStickyEvent(event);
    }
}























