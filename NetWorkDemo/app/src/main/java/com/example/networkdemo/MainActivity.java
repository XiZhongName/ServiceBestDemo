package com.example.networkdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    //private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpBinService httpBinService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //okHttpClient = new OkHttpClient();
        //创建Retrofit对象，并生成接口实现类对象
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpBinService = retrofit.create(HttpBinService.class);
    }

//    public void getSync(View view) {
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
//                //准备好请求的call对象
//                Call call = okHttpClient.newCall(request);
//                try {
//                    Response response = call.execute();
//                    Log.i(TAG, "getSync: " + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    public void getAsync(View view) {
//        Request request = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
//        Call call = okHttpClient.newCall(request);
//        //异步请求
//        call.enqueue(new Callback() {//enqueue内部会创建子线程
//            @Override//请求失败的回调
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override//请求结果回调
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                if (response.isSuccessful()){
//                    Log.i(TAG, "onResponse: " + response.body().string());
//                }
//            }
//        });
//    }
//
//    public void postSync(View view) {
//        new Thread(){
//            @Override
//            public void run() {
//                FormBody formBody = new FormBody.Builder()
//                        .add("a", "1").add("b", "2").build();
//                Request request = new Request.Builder().url("https://www.httpbin.org/post")
//                        .post(formBody).build();
//                Call call = okHttpClient.newCall(request);
//                try {
//                    Response response = call.execute();
//                    Log.i(TAG, "run: " + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }.start();
//    }

    public void postAsync(View view) {
        retrofit2.Call<ResponseBody> call = httpBinService.post("hui", "123");
        call.enqueue(new retrofit2.Callback<ResponseBody>(){

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
























