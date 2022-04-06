package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MNViewGroup extends ViewGroup {

    private int mWidth;//自定义viewGroup的宽度
    private int mHeight;

    public MNViewGroup(Context context) {
        super(context);
    }

    public MNViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MNViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //布局，确定子view在父view中的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left,right,top,bottom;
        int childCount = getChildCount();//获取viewGroup里有多少子元素
        int countTop = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
            //创建MarginLayoutParams对象才能修改margin值，需要重写generateLayoutParams方法
            left = layoutParams.leftMargin + getPaddingLeft();//线性垂直布局条件下
            top = countTop + layoutParams.topMargin + getPaddingTop();
            right = left + view.getMeasuredWidth();
            bottom = top + view.getMeasuredHeight();
            view.layout(left,top,right,bottom);
            countTop += (bottom - top) + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //没有子view的情况
        int childCount = getChildCount();//getChildCount返回子元素俺的个数
        if (childCount == 0){
            mWidth = measureWidthAndHeight(widthMeasureSpec);
            mHeight = measureWidthAndHeight(heightMeasureSpec);
            setMeasuredDimension(mWidth,mHeight);//设置当前view的大小
        } else {
            int childViewsWidth = 0;//子view的宽度
            int childViewsHeight = 0;
            int childViewsMarginTop = 0;
            int childViewsMarginBottom = 0;
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                measureChild(childView,widthMeasureSpec,heightMeasureSpec);//测量子view的宽高
                MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
                //获得viewGroup里所有view最大的宽度值
                childViewsWidth = Math.max(childViewsWidth,childView.getMeasuredWidth() + mp.leftMargin + mp.rightMargin);
                childViewsHeight += childView.getMeasuredHeight();
                //累加margin值
                childViewsMarginTop += mp.topMargin;
                childViewsMarginBottom += mp.bottomMargin;
            }
            mWidth = childViewsWidth + getPaddingLeft() + getPaddingRight();
            mHeight = childViewsHeight + getPaddingTop() + getPaddingBottom() +
            childViewsMarginTop + childViewsMarginBottom;
            setMeasuredDimension(measureWidthAndHeight(widthMeasureSpec,mWidth),measureWidthAndHeight(heightMeasureSpec,mHeight));
        }
    }

    private int measureWidthAndHeight(int measureSpec, int size) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        } else {
            result = size;
        }
        return result;
    }

    private int measureWidthAndHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        } else {
            result = 0;
        }
        return result;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}






























