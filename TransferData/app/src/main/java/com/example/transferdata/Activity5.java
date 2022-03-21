package com.example.transferdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

public class Activity5 extends AppCompatActivity {

    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        Intent intent = getIntent();
        if (intent != null) {
            User user = (User) intent.getSerializableExtra("user");
            Toast.makeText(Activity5.this, "这是传递过来的数据：" + user, Toast.LENGTH_LONG).show();
            Log.d(TAG,"这是传递过来的数据" + user);
        }
    }
}