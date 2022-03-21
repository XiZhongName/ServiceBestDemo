package com.example.order.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.order.R;
import com.example.order.adapter.MyAdapter;
import com.example.order.bean.ShopBean;
import com.example.order.utils.Constants;
import com.example.order.utils.JsonParse;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private RecyclerView rvShopView;
    private MyAdapter myAdapter;
    private List<String> mShopBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        for (int i=0;i<15;i++){
            mShopBean.add(""+i);
        };

        /*OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constants.WEB_SITE + Constants.REQUEST_SHOP_DATA).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                mShopBean = JsonParse.getInstance().getShopList(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });*/
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        rvShopView = findViewById(R.id.rv_shop_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
//设置布局管理器
//设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        rvShopView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(this, mShopBean);
        rvShopView.setAdapter(myAdapter);

    }
}

































