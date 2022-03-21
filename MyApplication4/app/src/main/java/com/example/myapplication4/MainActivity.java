package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected  static  final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(false);//设置是否选中
        boolean isChecked = checkBox.isChecked();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG,"onCreate,isChecked:"+isChecked);
            }
        });
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setProgress(80);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG,"onProgress:"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG,"onStartTrackingTouch:"+seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG,"onStop:"+seekBar.getProgress());
            }
        });
        TextView textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.li_bai);
                Intent intent = new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent);
            }
        });

    }
}