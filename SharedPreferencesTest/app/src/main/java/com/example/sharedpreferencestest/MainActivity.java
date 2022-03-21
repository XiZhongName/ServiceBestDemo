package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("名字","王麻子");
                editor.putInt("年龄",28);
                editor.putBoolean("是否结婚",false);
                editor.apply();
            }
        });
        Button restoreData = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
                String name = pref.getString("名字","");
                int age = pref.getInt("年龄",0);
                boolean married = pref.getBoolean("是否结婚",false);
                Log.d("MainActivity","名字是"+name);
                Log.d("MainActivity","年龄是"+age);
                Log.d("MainActivity","婚姻状况"+married);

            }
        });
    }
}