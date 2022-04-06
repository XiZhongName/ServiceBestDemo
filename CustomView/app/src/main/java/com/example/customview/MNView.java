package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
/*
1.继承View后需要实现三个构造方法（前三个）
2.修改前两个构造函数super改为this,并修改参数
3.初始化
4.绘制（在onDraw方法里）
5.对自定义控件进行测量（）,设置宽高setMeasuredDimension
**/
public class MNView extends View {

    private Rect mTextBounds;
    private Paint mPaint;
    private String text;
    private int width;//自定义控件的宽度
    private int height;//自定义控件的高度

    public MNView(Context context) {
        this(context,null);
    }

    public MNView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MNView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //通过obtainStyledAttributes获取TypedArray
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MNView);
        //从TypedArray中提取相应的属性值
        text = ta.getString(R.styleable.MNView_mn_text);
        ta.recycle();//释放内存
        mPaint = new Paint();//创建画笔
        mPaint.setTextSize(80);//设置绘制的字体大小
        mTextBounds = new Rect();//获取矩形对象
        mPaint.getTextBounds(text,0,text.length(),mTextBounds);//获取文字的显示范围
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,0 + getPaddingLeft(),mTextBounds.height() + getPaddingTop(),mPaint);//绘制
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//widthMeasureSpec、heightMeasureSpec来自父控件
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);//提取输入的宽度的值
        int specMode = MeasureSpec.getMode(widthMeasureSpec);//提取输入的宽度的模式
        if (specMode == MeasureSpec.EXACTLY){//EXACTLY代表(固定值)match_parent或者具体数值
            width = specWidth;//该模式下宽度等于传入的宽度
        } else if (specMode == MeasureSpec.AT_MOST){//AT_MOST代表wrap_content模式
       //     width = mTextBounds.width();//该模式下的宽度等于矩形的宽度
            width = mTextBounds.width() + getPaddingLeft() + getPaddingRight();//自定义的控件设置padding时需要加入边距
        }
        specMode = MeasureSpec.getMode(heightMeasureSpec);//提取输入的高度的模式
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            height = specHeight;
        } else if (specMode == MeasureSpec.AT_MOST){
         //   height = mTextBounds.height();
            height = mTextBounds.height() + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(width,height);//设置自定义控件需要的宽度和高度
    }
}












































