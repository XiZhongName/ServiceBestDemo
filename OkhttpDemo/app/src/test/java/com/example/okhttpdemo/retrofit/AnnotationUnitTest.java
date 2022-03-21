package com.example.okhttpdemo.retrofit;


import com.example.okhttpdemo.HttpbinService;

import org.junit.Test;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnnotationUnitTest {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
    HttpbinService httpbinService = retrofit.create(HttpbinService.class);

    @Test
    public void bodyTest() throws IOException {
        FormBody formBody = new FormBody.Builder()
                .add("q","5").add("w","2").build();
        Response<ResponseBody> response = httpbinService.postBody(formBody).execute();
        System.out.println(response.body().string());
    }
}
