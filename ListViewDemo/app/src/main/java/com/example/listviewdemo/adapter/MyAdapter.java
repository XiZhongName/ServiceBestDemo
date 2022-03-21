package com.example.listviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewdemo.R;
import com.example.listviewdemo.bean.ItemBean;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<ItemBean> mBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MyAdapter(Context context,List<ItemBean> beanList){
        this.mContext = context;
        this.mBeanList = beanList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(R.layout.list_item_layout,parent,false);
        ImageView imageView = convertView.findViewById(R.id.iv_img);
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvContent = convertView.findViewById(R.id.tv_content);
        ItemBean itemBean = mBeanList.get(position);
        imageView.setImageResource(itemBean.getImgResId());
        tvTitle.setText(itemBean.getTitle());
        tvContent.setText(itemBean.getContent());
        return convertView;
    }
}
