package com.example.photoalbum.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.photoalbum.R;
import com.example.photoalbum.adapter.AlbumAdapter;
import com.example.photoalbum.bean.PicBean;
import com.example.photoalbum.util.MySQLiteOpenHelper2;

import java.util.ArrayList;
import java.util.List;

public class BigPicActivity extends AppCompatActivity {

    private String imgPath;
    private ImageView imageView,iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_pic);
        imageView = findViewById(R.id.iv_pic_big);
        iv_back= findViewById(R.id.iv_back);
        imgPath = getIntent().getStringExtra("imgPath");
        Log.e("========00000", "onCreate: " + imgPath);
        Glide.with(this).load(imgPath).into(imageView);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}