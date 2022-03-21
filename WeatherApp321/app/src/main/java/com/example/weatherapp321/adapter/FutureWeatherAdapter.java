package com.example.weatherapp321.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp321.R;
import com.example.weatherapp321.bean.DayWeatherBean;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {
    private Context mContext;
    private List<DayWeatherBean> mWeatherBeans;

    public FutureWeatherAdapter(Context mContext, List<DayWeatherBean> WeatherBeans) {
        this.mContext = mContext;
        this.mWeatherBeans = WeatherBeans;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, parent, false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DayWeatherBean weatherBean = mWeatherBeans.get(position);
        holder.tvWeatherDate.setText(weatherBean.getDate());
        holder.tvWeather.setText(weatherBean.getWea());
        holder.tvWin.setText(weatherBean.getWin()[0] + weatherBean.getWinSpeed());
        holder.tvAir.setText("空气：" + weatherBean.getAir() + weatherBean.getAirLevel());
        holder.tvTem.setText(weatherBean.getTem());
        holder.tvTemLowHigh.setText(weatherBean.getTem2() + "~" + weatherBean.getTem1());
        holder.imageView.setImageResource(getImgResOfWeather(weatherBean.getWeaImg()));
    }

    @Override
    public int getItemCount() {
        return (mWeatherBeans == null) ? 0 : mWeatherBeans.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeather, tvTem, tvTemLowHigh, tvWin, tvAir,tvWeatherDate;
        ImageView imageView;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeatherDate = itemView.findViewById(R.id.tv_weather_date);
            tvWeather = itemView.findViewById(R.id.tv_weather);
            tvTem = itemView.findViewById(R.id.tv_tem);
            tvTemLowHigh = itemView.findViewById(R.id.tv_tem_low_high);
            tvAir = itemView.findViewById(R.id.tv_air);
            tvWin = itemView.findViewById(R.id.tv_win);
            imageView = itemView.findViewById(R.id.iv_weather);
        }
    }

    private int getImgResOfWeather(String weaStr) {
        //xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (weaStr) {
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
}
