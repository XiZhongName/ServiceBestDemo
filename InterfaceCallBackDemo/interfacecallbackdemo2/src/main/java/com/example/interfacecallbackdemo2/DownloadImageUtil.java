package com.example.interfacecallbackdemo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadImageUtil {
    public static void startDownload(final ImageStateInterface imageStateInterface, final Context context){
        imageStateInterface.onImageStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
                imageStateInterface.onImageSuccess(bitmap);
            }
        }).start();
        imageStateInterface.onEnd();
    }
}
