package com.example.glide40test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://guolin.tech/book.png";
                // Log.e("tag", "onClick: " + url);
                RequestOptions options = new RequestOptions()
                        .override(200, 200)//指定图片大小
                        .placeholder(R.drawable.yueliang)//占位图
                        .error(R.drawable.yueliang)//异常占位图，图片加载失败时显示
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用硬盘缓存功能，有5个可选参数
                        //NONE表示不缓存任何内容，DATA表示只缓存原始图片，RESOURCE表示只缓存转换过的图片
                        //ALL表示既缓存原始图片，也缓存转换后的图片
                        //AUTOMATIC（默认）表示让Glide根据图片资源智能的选择使用哪一种缓存策略
                        .skipMemoryCache(true);//禁用内存缓存功能
                Glide.with(MainActivity.this)
                        .asBitmap()//只允许加载静态图片,asGif()只允许加载动态图片
                     .load(url)
                     .apply(options)
                     .into(imageView);//preload可以替换into使用预加载功能
                //preload()方法有两个方法重载，一个不带参数，表示将会加载图片的原始尺寸，另一个可以通过参数指定加载图片的宽和高
            }
        });
    }
}
















