package com.example.noteapp.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.EditActivity;
import com.example.noteapp.NoteDbOpenHelper;
import com.example.noteapp.R;
import com.example.noteapp.bean.Note;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Note> mBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private NoteDbOpenHelper mNoteDbOpenHelper;
    private int viewType;
    public static int TYPE_LINEAR_LAYOUT = 0;
    public static int TYPE_GRID_LAYOUT = 0;

    public MyAdapter(List<Note> mBeanList, Context Context) {
        this.mBeanList = mBeanList;
        this.mContext = Context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mNoteDbOpenHelper = new NoteDbOpenHelper(mContext);


    }
    public void setViewType(int viewType){
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LINEAR_LAYOUT){
            View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else if (viewType == TYPE_GRID_LAYOUT){
            View view = mLayoutInflater.inflate(R.layout.item_menu_grid, parent, false);
            MyGridViewHolder myGridViewHolder = new MyGridViewHolder(view);
            return myGridViewHolder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder == null){
            return;
        }
        if (holder instanceof MyViewHolder){
            bindMyViewHolder((MyViewHolder) holder,position);
        } else if (holder instanceof MyGridViewHolder){
            bindGridViewHolder((MyGridViewHolder) holder,position);
        }

    }

    private void bindGridViewHolder(MyGridViewHolder holder, int position) {
        Note note = mBeanList.get(position);
        holder.mTvTitle.setText(note.getTitle());
        holder.mTvContent.setText(note.getContent());
        holder.mTvTime.setText(note.getCreatedTime());
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("note",note);
                mContext.startActivity(intent);
            }
        });
        holder.rlContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按功能
                Dialog dialog = new Dialog(mContext, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                View view = mLayoutInflater.inflate(R.layout.list_item_dialog_layout, null);
                TextView tvDelete = view.findViewById(R.id.tv_delete);
                TextView teEdit = view.findViewById(R.id.tv_edit);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int row = mNoteDbOpenHelper.deleteFromDbById(note.getId());
                        if (row > 0 ){
                            removeData(position);
                            Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                teEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,EditActivity.class);
                        intent.putExtra("note",note);
                        mContext.startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();

                return false;
            }
        });
    }

    private void bindMyViewHolder(MyViewHolder holder,int position){
        Note note = mBeanList.get(position);
        holder.mTvTitle.setText(note.getTitle());
        holder.mTvContent.setText(note.getContent());
        holder.mTvTime.setText(note.getCreatedTime());
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("note",note);
                mContext.startActivity(intent);
            }
        });
        holder.rlContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按功能
                Dialog dialog = new Dialog(mContext, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                View view = mLayoutInflater.inflate(R.layout.list_item_dialog_layout, null);
                TextView tvDelete = view.findViewById(R.id.tv_delete);
                TextView teEdit = view.findViewById(R.id.tv_edit);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int row = mNoteDbOpenHelper.deleteFromDbById(note.getId());
                        if (row > 0 ){
                            removeData(position);
                            Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                teEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,EditActivity.class);
                        intent.putExtra("note",note);
                        mContext.startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }
    //刷新数据
    public void refreshData(List<Note> notes){

        this.mBeanList = notes;
        notifyDataSetChanged();//通知列表数据刷新，自己调用onCreate等一系列方法
    }
    //删除数据
    public void removeData(int pos){
        mBeanList.remove(pos);
        notifyItemRemoved(pos);
    }

    class MyGridViewHolder extends RecyclerView.ViewHolder{
        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvTime;
        ViewGroup rlContainer;

        public MyGridViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvContent = itemView.findViewById(R.id.tv_content);
            this.mTvTime = itemView.findViewById(R.id.tv_time);
            this.rlContainer = itemView.findViewById(R.id.rl_item_grid);
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvTime;
        ViewGroup rlContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvContent = itemView.findViewById(R.id.tv_content);
            this.mTvTime = itemView.findViewById(R.id.tv_time);
            this.rlContainer = itemView.findViewById(R.id.rl_item_container);
        }
    }


}
