package com.example.recyclerview261.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview261.R;
import com.example.recyclerview261.bean.ItemBean;

import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ItemBean> mItemBean;

    public MyAdapter(Context mContext, List<ItemBean> mItemBean) {
        this.mContext = mContext;
        this.mItemBean = mItemBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemBean itemBean = mItemBean.get(position);
        holder.mTvTitle.setText(itemBean.getTitle());
        holder.mContent.setText(itemBean.getContent());
        holder.myIvImage.setImageResource(itemBean.getImgResId());
        Random random = new Random();
        int ran = random.nextInt(40) - 10;
        holder.myIvImage.setLayoutParams(new RelativeLayout.LayoutParams(dp2px(100), dp2px(100 + ran)));
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "你点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mItemBean.size();
    }

    //像素和dp的转换
    private int dp2px(int dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        ImageView myIvImage;
        TextView mContent;
        RelativeLayout rlContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mContent = itemView.findViewById(R.id.tv_content);
            this.myIvImage = itemView.findViewById(R.id.iv_img);
            this.rlContainer = itemView.findViewById(R.id.rl_item_container);
        }
    }
}















