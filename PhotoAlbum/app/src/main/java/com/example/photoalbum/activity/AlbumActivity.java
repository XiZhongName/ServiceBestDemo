package com.example.photoalbum.activity;

import static com.example.photoalbum.util.MySQLiteOpenHelper2.TABLE_NAME_STUDENT;
import static com.example.photoalbum.util.MySQLiteOpenHelper3.TABLE_NAME_PIC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.photoalbum.R;
import com.example.photoalbum.adapter.AlbumAdapter;
import com.example.photoalbum.bean.EventUpdateBean;
import com.example.photoalbum.bean.PhotoBean;
import com.example.photoalbum.bean.PicBean;
import com.example.photoalbum.util.MySQLiteOpenHelper2;
import com.example.photoalbum.util.MySQLiteOpenHelper3;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity implements AlbumAdapter.OnItemClickListener, AlbumAdapter.DeleteCheckBoxPic {
    RecyclerView recyclerView;
    RelativeLayout re_opt;
    AlbumAdapter albumAdapter;
    private List<PicBean> picList = new ArrayList();
    LinearLayout ll_selectTitle;//全选
    private int allSelect;//全选次数
    private ImageView iv_back;//返回按钮
    TextView tv_delete_photo;//删除按钮
    List<PicBean> deleteDataList;
    private MySQLiteOpenHelper3 helper;
    SQLiteDatabase db;
    ContentValues values = new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        helper = new MySQLiteOpenHelper3(this);
        db = helper.getWritableDatabase();
       // picList = getIntent().getParcelableArrayListExtra("picList");
        //edittext 数据存储到SQLite
        /*for(int i=0;i<picList.size();i++){
            values.put("name",picList.get(i).getImgPath());
        }
        db.insert(TABLE_NAME_PIC, null, values);*/
        picList = queryFromDbAll();
        Log.e("=============qqq", "onCreate: " + picList.size());
        recyclerView = findViewById(R.id.recyclerview_album);
        albumAdapter = new AlbumAdapter(AlbumActivity.this);
        albumAdapter.setmListener(this);
        albumAdapter.setDeleteCheckBox(this);
        albumAdapter.setData(picList);
        //设置RecyclerView为网格布局
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(albumAdapter);
        ll_selectTitle = findViewById(R.id.ll_selectTitle);
        iv_back = findViewById(R.id.iv_back);
        tv_delete_photo = findViewById(R.id.tv_delete_photo);
        //点击删除的监听事件
        tv_delete_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < deleteDataList.size(); i++) {
                    helper.deleteFromDbByName(deleteDataList.get(i).getImgPath());
                }
                picList.removeAll(deleteDataList);
                albumAdapter.setData(picList);
                albumAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new EventUpdateBean("update",picList));
            }
        });
        //点击返回后结束当前页面
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        re_opt = findViewById(R.id.re_opt);
        //点击全选的监听事件
        ll_selectTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allSelect++;
                //添加删除点击次数为奇数显示，偶数隐藏
                if (allSelect % 2 == 0) {
                    re_opt.setVisibility(View.VISIBLE);
                    albumAdapter.showCheckBox(true);
                } else {
                    re_opt.setVisibility(View.GONE);
                    albumAdapter.showCheckBox(false);
                }
            }
        });

    }

    //点击图片后跳转到大图预览
    @Override
    public void onItemClick(String picPath) {
        Intent intent = new Intent(AlbumActivity.this, BigPicActivity.class);
        intent.putExtra("imgPath", picPath);
        startActivity(intent);
    }

    @Override
    public void getDeleteDataPic(List<PicBean> deleteData, int position) {
        deleteDataList = deleteData;
        Log.e("=========tttt", deleteDataList.size() + "");
    }

    public List<PicBean> queryFromDbAll() {
        Cursor cursor = db.query(TABLE_NAME_PIC, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String imgPath = cursor.getString(cursor.getColumnIndex("path"));
                PicBean picBean = new PicBean();
                Log.e("=========tttt111", imgPath);
                picBean.setImgPath(imgPath);
                picList.add(picBean);
            }
            cursor.close();
        }
        return picList;
    }

}