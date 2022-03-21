package com.example.weatherapp321;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weatherapp321.adapter.FutureWeatherAdapter;
import com.example.weatherapp321.bean.DayWeatherBean;
import com.example.weatherapp321.bean.WeatherBean;
import com.example.weatherapp321.util.NetUtil;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;//定义下拉框的适配器
    private String[] mCities;
    private TextView tvWeather,tvTem,tvTemLowHigh,tvWin,tvAir;
    private ImageView ivWeather;
    private RecyclerView rlvFutureWeather;
    private FutureWeatherAdapter mWeatherAdapter;
    private DayWeatherBean todayWeather;

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String weather = (String) msg.obj;
                Log.d("tag","--weather--" + weather);
                //解析json数据为java对象
                Gson gson = new Gson();//需要导入依赖库
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                Log.d("tag","--解析后的数据--" + weatherBean.toString());
                updateUiOfWeather(weatherBean);//数据填充
            }
        }
    };

    private void updateUiOfWeather(WeatherBean weatherBean) {
        if (weatherBean == null){
            return;
        }
        List<DayWeatherBean> dayWeathers = weatherBean.getDayWeathers();
        todayWeather = dayWeathers.get(0);
        if (todayWeather == null){
            return;
        }
        tvTem.setText(todayWeather.getTem());
        tvWeather.setText(todayWeather.getWea() + "(" + todayWeather.getDate() + todayWeather.getWeek() + ")");
        tvTemLowHigh.setText(todayWeather.getTem2() + "~" + todayWeather.getTem1());
        tvWin.setText(todayWeather.getWin()[0] + todayWeather.getWinSpeed());
        tvAir.setText("空气："+todayWeather.getAir() + todayWeather.getAirLevel() + "\n" + todayWeather.getAirTips());
        ivWeather.setImageResource(getImgResOfWeather(todayWeather.getWeaImg()));//设置天气图片
        dayWeathers.remove(0);//去掉当天的天气
        //未来6天的天气展示
        mWeatherAdapter = new FutureWeatherAdapter(this,dayWeathers);
        rlvFutureWeather.setAdapter(mWeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rlvFutureWeather.setLayoutManager(layoutManager);
    }
    //根据天气更换相应的图片
    private int getImgResOfWeather(String weaStr){
        //xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (weaStr){
            case "qing":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            case "yin":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "yu":
                result = R.drawable.biz_plugin_weather_xiaoyu;
                break;
            case "yun":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "bingbao":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "wu":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "shachen":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "lei":
                result = R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "xue":
                result = R.drawable.biz_plugin_weather_xiaoxue;
                break;
            default:
                result = R.drawable.biz_plugin_weather_qing;
                break;
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);//关联数据
        mSpAdapter = new ArrayAdapter<>(this,R.layout.sp_item_layout,mCities);//把数据放进适配器里
        mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override//一个控件被选中时调用这个
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = mCities[position];//选中的城市

                getWeatherOfCity(selectedCity);
            }

            @Override//没有被选中时调用这个
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvWeather = findViewById(R.id.tv_weather);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        tvWin = findViewById(R.id.tv_win);
        tvAir = findViewById(R.id.tv_air);
        ivWeather = findViewById(R.id.iv_weather);
        rlvFutureWeather = findViewById(R.id.rlv_future_weather);
    }

    private void getWeatherOfCity(String selectedCity) {
        //开启子线程，请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherOfCity = NetUtil.getWeatherOfCity(selectedCity);
                //使用Handler将数据传递给主线程
                Message message = Message.obtain();//创建消息
                message.what = 0;//标识
                message.obj = weatherOfCity;//把数据放进消息
                mHandler.sendMessage(message);
            }
        }).start();
    }

    public void airDetails(View view) {
        Intent intent = new Intent(this,TipsActivity.class);
        intent.putExtra("tips",todayWeather);
        startActivity(intent);
    }
}