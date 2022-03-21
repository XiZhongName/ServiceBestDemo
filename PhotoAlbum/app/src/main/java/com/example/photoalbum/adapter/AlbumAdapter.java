package com.example.photoalbum.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photoalbum.R;
import com.example.photoalbum.bean.PhotoBean;
import com.example.photoalbum.bean.PicBean;
import com.example.photoalbum.util.MySQLiteOpenHelper2;
import com.example.photoalbum.util.SelectEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<PicBean> mPicList = new ArrayList<>();//数据源
    private AlbumAdapter.OnItemClickListener mListener;

    private EventBus eventBus;
    public HashMap<Integer,Boolean> map;
    private List<PicBean> selected;

    public void setDeleteCheckBox(DeleteCheckBoxPic deleteCheckBox) {
        this.deleteCheckBox = deleteCheckBox;
    }

    public DeleteCheckBoxPic deleteCheckBox;
    public AlbumAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_pic, parent, false);
        AlbumAdapter.AlbumViewHolder viewHolder = new AlbumAdapter.AlbumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PicBean picBean = mPicList.get(position);
        Log.e("=====999KKKK", "" + mPicList.get(position).getImgPath());
        Glide.with(mContext)
                .load(mPicList.get(position).getImgPath())
                .centerCrop()
                .into(holder.iv_item);//给图库设置封面
        //获取相册名字
        if (mPicList.get(position).isCheckBoxShow()) {
            holder.checkbox_item.setVisibility(View.VISIBLE);
        } else {
            holder.checkbox_item.setVisibility(View.INVISIBLE);
        }
        Log.e("=====5555KKKK", "" + mPicList.get(position).isCheck());
        holder.checkbox_item.setChecked(mPicList.get(position).isCheck());
        holder.checkbox_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicList.add(picBean);
                deleteCheckBox.getDeleteDataPic(mPicList, holder.getAdapterPosition());
            }
        });
        holder.iv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(mListener!=null){
                mListener.onItemClick(mPicList.get(position).getImgPath());
            }
                int mFlags = (Integer)v.getTag();
                if (map != null) {
                    if (map.get(position)) {
                        map.put(position, false);
                        eventBus.post(new SelectEvent(selected(map)));
                    } else {
                        map.put(mFlags, Boolean.TRUE);
                        eventBus.post(new SelectEvent(selected(map)));
                    }
                }
            }
        });

    }

    private int selected(HashMap<Integer,Boolean> map){
        int size = 0;
        for (Integer key : map.keySet()){
            if (map.get(key)){
                size++;
            }
        }
        return size;
    }

    @Override
    public int getItemCount() {
        return mPicList.size();
    }

    public void setmListener(AlbumAdapter.OnItemClickListener mListener) {
        this.mListener = mListener;
    }


    class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item;
        CheckBox checkbox_item;
        View itemView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_item = itemView.findViewById(R.id.iv_item);
            checkbox_item = itemView.findViewById(R.id.checkbox_item);
        }
    }

    public void setData(List<PicBean> dataList) {
        Log.e("=====ggg", "" + dataList.size());
        if (mPicList != null) {
            mPicList.clear();
            mPicList.addAll(dataList);
            Log.e("=====gggkkk", "" + mPicList.size());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String picPath);
    }
    public void showCheckBox(Boolean isShow) {
        for (int i = 0; i < mPicList.size(); i++) {
            if (isShow) {
                mPicList.get(i).setCheckBoxShow(true);
            } else {
                mPicList.get(i).setCheckBoxShow(false);
            }
            notifyDataSetChanged();
        }
    }
    public interface DeleteCheckBoxPic {
        void getDeleteDataPic(List<PicBean> deleteData, int p);
    }
}
