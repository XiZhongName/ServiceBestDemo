package com.example.networkdemo;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpBinService {
    @POST("post")
    @FormUrlEncoded
   Call<ResponseBody> post(@Field("username") String userName, @Field("password") String pwd);//Call引入的类是 retrofit2.Call
    //Response引入的是okhttp3
    @GET("get")
    Call<ResponseBody> get(@Query("username") String userName, @Query("password") String pwd);

//    @POST("post")
//    Call<ResponseBody> postBody(@Body RequestBody body);
}




























