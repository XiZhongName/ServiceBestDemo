package com.example.okhttpdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpbinService httpbinService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpClient = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpbinService = retrofit.create(HttpbinService.class);

    }

    public void getSync(View view) {
       new Thread(){
           @Override
           public void run() {
               super.run();
               Request request = new Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
               //准备好请求的call对象
               Call call = okHttpClient.newCall(request);
               try {
                   Response response = call.execute();
                   Log.i(TAG, "getSync: " + response.body().string());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }.start();
    }

    public void getAsync(View view) {
        Request request = new Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
        Call call = okHttpClient.newCall(request);
        //异步请求
        call.enqueue(new Callback() {//enqueue内部会创建子线程
            @Override//请求失败的回调
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override//请求结果回调
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    Log.i(TAG, "onResponse: " + response.body().string());
                }
            }
        });
    }

    public void postSync(View view) {
        new Thread(){
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder()
                        .add("a", "1").add("b", "2").build();
                Request request = new Request.Builder().url("https://www.httpbin.org/post")
                        .post(formBody).build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.i(TAG, "run: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    public void postAsync(View view) {
//        FormBody formBody = new FormBody.Builder()
//                .add("a", "4").add("b", "8").build();
//        Request request = new Builder().url("https://www.httpbin.org/post").post(formBody).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Log.i(TAG, "onResponse: " + response.body().string());
//            }
//        });
        retrofit2.Call<ResponseBody> call = httpbinService.post("hui", "123");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {
                    Log.i(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}