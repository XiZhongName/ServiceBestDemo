package com.example.eventbusdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventbusdemo2.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //EventBus.getDefault().register(this);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("hello"));
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//
//    }
//     public class ButtonListener{
//        public void buttonOnclick(View view){
//            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//            startActivity(intent);
//        }
//
////    }
//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void onMessageEvent(MessageEvent event){
//        String message = event.getMessage().toString();
//        binding.button.setText(message);
//    }

}























