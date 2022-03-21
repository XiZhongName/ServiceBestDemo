package com.example.touchdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {
    int x = 100, y = 150;
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public void setXY(int _x, int _y){
        x = _x;
        y = _y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.CYAN);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x,y,30,paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x-6,y-6,6,paint);
    }
}


























