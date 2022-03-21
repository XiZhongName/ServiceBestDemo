package com.example.interfacecallbackdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements ImageStateInterface{
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadImageUtil.startDownload(MainActivity2.this,getApplicationContext());
            }
        });
    }

    @Override
    public void onImageStart() {
        button.setText("onImageStart");
        textView.setText("onImageStart");
    }

    @Override
    public void onImageSuccess(Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setText("onImageSuccess");
                textView.setText("onImageSuccess");
                button.setBackground(new BitmapDrawable(bitmap));
            }
        });
    }

    @Override
    public void onImageFailed() {

    }

    @Override
    public void onEnd() {
        Toast.makeText(MainActivity2.this, "完成啦", Toast.LENGTH_SHORT).show();
    }
}


















