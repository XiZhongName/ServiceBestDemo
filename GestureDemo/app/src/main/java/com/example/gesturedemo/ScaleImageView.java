package com.example.gesturedemo;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ScaleImageView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener , View.OnTouchListener {
    ScaleGestureDetector mScaleGestureDetector;
    Matrix mScaleMatrix = new Matrix();//矩阵
    float initScale = 1.0f;//初始化时放大的倍数
    static final float SCALE_MAX = 6.0f;//放大的最大倍数
    float[] matrixValues = new float[9];//保存放大后的坐标

    public ScaleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaleGestureDetector = new ScaleGestureDetector(context,this);
        //super.setScaleType(ScaleType.MATRIX);设置缩放，可以在activity里设置，也可以在这里设置
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();//当前缩放比例
        float scaleFactor = detector.getScaleFactor();//事件触发后的基于原来缩放比例再次缩放后的比例

        if ((scale < SCALE_MAX && scaleFactor > 1.0f ) || (scale > initScale && scaleFactor < 1.0f)){
            if (scaleFactor * scale < initScale){
                scaleFactor = initScale/scale;
            }
            if (scaleFactor * scale > SCALE_MAX){
                scaleFactor = SCALE_MAX/scale;
            }
        }
        //mScaleMatrix.postScale(scaleFactor,scaleFactor,getWidth()/2,getHeight()/2);//以图片中心点放大
        mScaleMatrix.postScale(scaleFactor,scaleFactor,detector.getFocusX()/2,detector.getFocusY()/2);//以两个手指之间的中心点放大
        checkBorderAndCenterWhenScale();
        setImageMatrix(mScaleMatrix);//保存缩放后图片的效果
        return true;
    }

    private void checkBorderAndCenterWhenScale(){//检查是否有白边和超出边界
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;
        int width = getWidth();
        int height = getHeight();
        if (rectF.width() >= width){
            if (rectF.left > 0 ){
                deltaX = -rectF.left;
            }
            if (rectF.right < width){
                deltaX = width - rectF.right;
            }
        }
        if (rectF.height() >= height){
            if (rectF.top > 0){//说明图片与顶部有空白，需要上移
                deltaY = -rectF.top;
            }
            if (rectF.bottom < height){//图片与底部有空白，需要下移
                deltaY = height - rectF.bottom;
            }
        }
        if (rectF.width() < width){
            deltaX = width * 0.5f - rectF.right + 0.5f * rectF.width();
        }
        if (rectF.height() < height){
            deltaY = height * 0.5f - rectF.bottom + 0.5f * rectF.height();
        }
        mScaleMatrix.postTranslate(deltaX,deltaY);
    }

    private RectF getMatrixRectF() {//得到当前绘图区域的矩形边界
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();
        Drawable d = getDrawable();
        if (null != d){
            rectF.set(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    private float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }
}


































