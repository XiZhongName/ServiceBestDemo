package com.example.tablayoutdemo2.model5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * 图片模糊
 * */
public class ImageFilter {
    private static final float BITMAP_SCALE = 0.4f;//图片缩放比例
    //模糊图片的具体方法
    public static Bitmap blurBitmap(Context context,Bitmap image,float blurRadius){
        //计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);
        //将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image,width,height,false);
        //创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
        //创建RenderScript内核对象
        RenderScript rs = RenderScript.create(context);
        //创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //由于RenderScript并没有使用VM来分配内存，所以需要使用Allocation类来创建和分配内存空间
        //创建Allocation对象的时候其实内存是空的，需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs,inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs,outputBitmap);
        //设置渲染的模糊程度，25f是最大模糊度
        blurScript.setRadius(blurRadius);
        //设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        //将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        //将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }
    public static Bitmap blurBitmap(Context context,Bitmap bitmap){
        //用需要创建高斯模糊bitmap创建一个空的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        //初始化Renderscript,该类提供了RenderScript context,创建其他RS类之前必须先创建这个类，
        // 其控制RenderScript的初始化，资源管理及释放
        RenderScript rs = RenderScript.create(context);
        //创建高斯模糊对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs,Element.U8_4(rs));
        //创建Allocations,此类是将数据传递给RenderScript内核的主要方法，并指定一个后背类型存储给定类型
        Allocation allIn = Allocation.createFromBitmap(rs,bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs,outBitmap);
        //设定模糊度（注：Radius最大只能设置25.f）
        blurScript.setRadius(15.f);
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        allOut.copyTo(outBitmap);
        rs.hashCode();
        return outBitmap;
    }
}




















