package com.example.printdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new TestView(this));
    }
    private class TestView extends View{

        public TestView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.YELLOW);//设置背景色
            Paint paint = new Paint();//创建画笔
            paint.setStyle(Paint.Style.STROKE);//设置画笔样式为空心
            paint.setStrokeWidth(50);//设置空心画笔宽度
            paint.setAntiAlias(true);//去掉锯齿
            canvas.drawRect(100,100,500,500,paint);//绘制矩形
            paint.setStyle(Paint.Style.FILL);//设置画笔为实心
            canvas.drawRect(600,100,1000,500,paint);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(550,700,300,paint);//绘制圆形
            paint.setColor(Color.WHITE);
            canvas.drawCircle(510,720,20,paint);
            //绘制三角形
            paint.setColor(Color.RED);
            Path path = new Path();//引入线条
            path.moveTo(200,1000);//线条起点位置
            path.lineTo(900,1000);//绘制线条
            path.lineTo(550,1100);//绘制第二条线
            path.close();//绘制闭合线条
            canvas.drawPath(path,paint);
            //绘制文字
            paint.setTextSize(100);//设置字体大小
            paint.setColor(Color.GREEN);
            canvas.drawText("HELLO!~~",300,1200,paint);

        }
    }
}