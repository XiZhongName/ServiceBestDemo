package com.example.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.order.R;
import com.example.order.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<String> data;
    // private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, List<String> data) {
        this.context = context;
//        this.data.clear();
//        this.data.addAll(data);//不能改变this.data的地址，否则会导致无法更新
//        notifyDataSetChanged();//数据有变化，进行刷新
        this.data = data;
        //mLayoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //ShopBean shopBean = data.get(position);
        //给控件获取图片
        //Glide.with(context).load(shopBean.getShopPic()).error(R.mipmap.ic_launcher).into(holder.imageView);
        holder.tvShopName.setText(data.get(position));
       /* holder.tvTime.setText(shopBean.getTime());
        holder.tvWelfare.setText(shopBean.getWelfare());
        holder.tvSaleNum.setText("月售" + shopBean.getSaleNum());
        holder.tvOfferPrice.setText("起送￥" + shopBean.getOfferPrice() + "配送￥" + shopBean.getDistributionCost());*/

    }

    @Override
    public int getItemCount() {

        return (data == null) ? 0 : data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvShopName, tvSaleNum, tvOfferPrice, tvWelfare, tvTime;
        RelativeLayout rlContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.iv_shop_pic);
            this.tvShopName = itemView.findViewById(R.id.tv_shop_name);
            this.tvSaleNum = itemView.findViewById(R.id.tv_sale_num);
            this.tvOfferPrice = itemView.findViewById(R.id.tv_offer_price);
            this.tvWelfare = itemView.findViewById(R.id.tv_welfare);
            this.tvTime = itemView.findViewById(R.id.tv_time);
            this.rlContainer = itemView.findViewById(R.id.rv_container);
        }
    }
}


























