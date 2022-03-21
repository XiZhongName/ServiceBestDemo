package com.example.photoalbum.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photoalbum.R;
import com.example.photoalbum.activity.Photo;
import com.example.photoalbum.bean.PhotoBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<PhotoBean> mPhotoBean = new ArrayList<>();//数据源
    private int checkClickNumber;
    private List<PhotoBean> photoNameBean = new ArrayList<>();
    private OnItemClickListener mListener;
    public DeleteCheckBox deleteCheckBox;//删除的回调

    public void setDeleteCheckBox(DeleteCheckBox deleteCheckBox) {
        this.deleteCheckBox = deleteCheckBox;
    }


    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PhotoBean photoBean = mPhotoBean.get(position);
        //获取相册封面
        Log.e("=====999KKKK", "" + mPhotoBean.get(position).getImgPath());
        if (!TextUtils.isEmpty(mPhotoBean.get(position).getImgPath())) {
            Glide.with(mContext).load(mPhotoBean.get(position).getImgPath()).into(holder.imageView);
        } else {
            Glide.with(mContext).load(R.drawable.ic_baseline_add_photo_alternate_24).into(holder.imageView);
        }
        //设置相册名字
        holder.textView.setText(photoBean.getPhotoName());
        if (mPhotoBean.get(position).isCheckBoxShow()) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.INVISIBLE);
        }
        Log.e("=====5555KKKK", "" + photoBean.isCheck());
        holder.checkBox.setChecked(photoBean.isCheck());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClickNumber++;
                if (checkClickNumber % 2 == 0) {
                    mPhotoBean.get(holder.getAdapterPosition()).setCheck(true);
                } else {
                    mPhotoBean.get(holder.getAdapterPosition()).setCheck(false);
                }
                Log.e("=====4444KKKK", "" + holder.getAdapterPosition());
                photoNameBean.add(photoBean);
                deleteCheckBox.getDeleteData(photoNameBean, holder.getAdapterPosition());
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPhotoBean.size();
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        RelativeLayout rlContainer;
        CheckBox checkBox;
        View itemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.iv_photo_item);
            textView = itemView.findViewById(R.id.tv_photo_item);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

    public void showCheckBox(Boolean isShow) {
        for (int i = 0; i < mPhotoBean.size(); i++) {
            if (isShow) {
                mPhotoBean.get(i).setCheckBoxShow(true);
            } else {
                mPhotoBean.get(i).setCheckBoxShow(false);
            }
            ;
            notifyDataSetChanged();
        }
    }

    public interface DeleteCheckBox {
        void getDeleteData(List<PhotoBean> deleteData, int p);
    }

    public void setData(List<PhotoBean> dataList) {
        if (mPhotoBean != null) {
            mPhotoBean.clear();
            mPhotoBean.addAll(dataList);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}






















