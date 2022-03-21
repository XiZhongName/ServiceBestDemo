package com.example.interfacecallbackdemo2;

import android.graphics.Bitmap;

public interface ImageStateInterface {
    void onImageStart();
    void onImageSuccess(Bitmap bitmap);
    void onImageFailed();
    void onEnd();
}
