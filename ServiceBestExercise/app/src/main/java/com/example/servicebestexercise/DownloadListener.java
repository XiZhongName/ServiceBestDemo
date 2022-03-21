package com.example.servicebestexercise;

public interface DownloadListener {
    void onProgress(int progress);//通知下载进度
    void onSuccess();//通知下载成功事件
    void onFailed();//通知下载失败事件
    void onPaused();//通知下载暂停
    void onCanceled();//通知下载取消
}
