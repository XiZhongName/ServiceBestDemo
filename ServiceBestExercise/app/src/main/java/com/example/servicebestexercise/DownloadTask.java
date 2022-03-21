package com.example.servicebestexercise;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    //AsyncTask的三个参数，第一个参数表示在执行AsyncTask的时候需要传入一个字符串参数给后台任务
    //第二个表示使用整型数据来作为进度显示单位
    //第三个表示使用整型数据来反馈执行结果
    public static final int TYPE_SUCCESS = 0;//下载成功
    public static final int TYPE_FAILED = 1;//下载失败
    public static final int TYPE_PAUSED = 2;//下载暂停
    public static final int TYPE_CANCELED = 3;//下载取消
    private DownloadListener listener;//构造函数中通过此参数回调
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {//该方法会自动开启线程并在后台执行具体的下载逻辑
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadLength = 0;//记录已下载的文件长度
            String downloadUrl = strings[0];//获取到下载的url地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));//解析出要下载的文件名
            //指定将文件下载到Environment.DIRECTORY_DOWNLOADS目录下也就是SD卡的Download目录
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {//判断目录中是不是已经存在要下载的文件
                downloadLength = file.length();//记录已下载的字节数
            }
            long contentLength = getContentLength(downloadUrl);//获取待下载文件的总长度
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {//已下载字节和文件总字节相等说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);//跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        //计算已下载的百分比
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);//通知进度条更新
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {//该方法用于在界面上更新当前的下载进度
        super.onProgressUpdate(values);
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {//该方法用于通知最终的下载结果
        super.onPostExecute(integer);
        switch (integer) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl).build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
























