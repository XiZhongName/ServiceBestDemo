package com.example.servicelifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button startServiceBtn,startServiceBtn2,stopServiceBtn;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvText);
        startServiceBtn = findViewById(R.id.start_service_btn);
        startServiceBtn2 = findViewById(R.id.start_service_btn2);
        stopServiceBtn = findViewById(R.id.stop_service_btn);
        startServiceBtn.setOnClickListener(new MyClick());
        startServiceBtn2.setOnClickListener(new MyClick());
        stopServiceBtn.setOnClickListener(new MyClick());
        intent = new Intent(this,MyService.class);
    }
    class MyClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start_service_btn:
                    intent.putExtra("music","R.raw.qing");
                    MainActivity.this.startService(intent);
                    break;
                case R.id.start_service_btn2:
                    intent.putExtra("music","R.raw.ting");
                    MainActivity.this.startService(intent);
                    break;
                case R.id.stop_service_btn:
                    MainActivity.this.stopService(intent);
                    break;
            }
        }
    }
}