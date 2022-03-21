package com.example.asnyctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    TextView textView;
    ProgressBar pgb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.text);
        pgb = findViewById(R.id.pgb);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
    class TimeTickLoad extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }
    }
}
























