package com.example.weatherapp321.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp321.R;
import com.example.weatherapp321.bean.DayWeatherBean;
import com.example.weatherapp321.bean.OtherTipsBean;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    private Context mContext;
    private List<OtherTipsBean> mOtherTipsBean;

    public TipsAdapter(List<OtherTipsBean> tipsBeans,Context context) {
        mOtherTipsBean = tipsBeans;
        mContext = context;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tips_item_layout, parent, false);
        TipsViewHolder tipsViewHolder = new TipsViewHolder(view);
        return tipsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        OtherTipsBean otherTipsBean = mOtherTipsBean.get(position);
        holder.tvTitle.setText(otherTipsBean.getTitle());
        holder.tvLevel.setText(otherTipsBean.getLevel());
        holder.tvDesc.setText(otherTipsBean.getDesc());
    }

    @Override
    public int getItemCount() {
        return (mOtherTipsBean == null) ? 0 : mOtherTipsBean.size();
    }

    class TipsViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvLevel,tvDesc;
        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLevel = itemView.findViewById(R.id.tv_level);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
