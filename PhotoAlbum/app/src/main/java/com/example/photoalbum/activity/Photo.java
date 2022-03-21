package com.example.photoalbum.activity;

import static com.example.photoalbum.util.MySQLiteOpenHelper2.TABLE_NAME_STUDENT;
import static com.example.photoalbum.util.MySQLiteOpenHelper3.TABLE_NAME_PIC;
import static com.example.photoalbum.util.MySqliteOpenHelper.TABLE_NAME_PHOTO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.ContentLengthInputStream;
import com.example.photoalbum.R;
import com.example.photoalbum.adapter.MyAdapter;
import com.example.photoalbum.bean.EventUpdateBean;
import com.example.photoalbum.bean.PhotoBean;
import com.example.photoalbum.bean.PicBean;
import com.example.photoalbum.util.MySQLiteOpenHelper2;
import com.example.photoalbum.util.MySQLiteOpenHelper3;
import com.example.photoalbum.util.MySqliteOpenHelper;
import com.example.photoalbum.util.SelectEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Photo extends AppCompatActivity implements MyAdapter.DeleteCheckBox, MyAdapter.OnItemClickListener {
    private ImageView createPhotoButton;
    private boolean isQuit = false;
    private MySQLiteOpenHelper2 helper;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    SQLiteDatabase db;
    //3.9
    private CheckBox checkBox;//全选框
    private boolean isChange = false;
    private ArrayList<PhotoBean> list = new ArrayList<>();
    private EventBus eventBus;

    ContentValues values = new ContentValues();
    private List<PhotoBean> photoList = new ArrayList<>();
    LinearLayout ll_selectTitle;//全选
    RelativeLayout re_opt;//删除、添加的父布局
    TextView tv_add, tv_delete;//删除、添加控件
    private int allSelect;//点击全选的次数
    List<PhotoBean> deleteDataList;//删除的数据集合
    List<PicBean> choosePicPath = new ArrayList<>();//选择的图片路径的集合
    private MySQLiteOpenHelper3 helperPic;
    private SQLiteDatabase dbPic;
    ContentValues valuesPic = new ContentValues();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initView();
        initData();

    }

    private void initData() {
        //点击全选框的监听事件
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                HashMap<Integer, Boolean> map = new HashMap<>();
                if (isChecked){
                    isChange = false;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (isChecked){
                        map.put(i,true);
                    } else if (!isChange){
                        map.put(i,false);
                    } else {
                        map = myAdapter.getMap();
                    }
                }
                allSelect++;
                //点击全选时，奇数时显示添加，删除控件，偶数时隐藏控件
                if (allSelect % 2 == 0) {
                    re_opt.setVisibility(View.GONE);
                    myAdapter.showCheckBox(false);
                } else {
                    re_opt.setVisibility(View.VISIBLE);
                    myAdapter.showCheckBox(true);
                }

            }
        });
        /*checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allSelect++;
                //点击全选时，奇数时显示添加，删除控件，偶数时隐藏控件
                if (allSelect % 2 == 0) {
                    re_opt.setVisibility(View.GONE);
                    myAdapter.showCheckBox(false);
                } else {
                    re_opt.setVisibility(View.VISIBLE);
                    myAdapter.showCheckBox(true);
                }
            }
        });*/



        //点击添加的监听事件
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPhoto();//创建相册
            }
        });
        //点击删除的监听事件
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < deleteDataList.size(); i++) {
                    helper.deleteFromDbByName(deleteDataList.get(i).getPhotoName());
                }
                photoList.removeAll(deleteDataList);
                myAdapter.setData(photoList);
                myAdapter.notifyDataSetChanged();//刷新recyclerView
            }
        });


//        myAdapter.setData(photoList);
//        myAdapter.notifyDataSetChanged();
        //recyclerView.setAdapter(myAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Photo.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        createPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPhoto();
            }
        });
    }

    private void initView() {
        EventBus.getDefault().register(this);
        helper = new MySQLiteOpenHelper2(this);
        helperPic = new MySQLiteOpenHelper3(this);
        db = helper.getWritableDatabase();
        dbPic = helperPic.getWritableDatabase();
        myAdapter = new MyAdapter(Photo.this);//改参数
        //myAdapter.setDeleteCheckBox(this);
        myAdapter.setmListener(this);
        recyclerView = findViewById(R.id.recyclerview_photo);
        createPhotoButton = findViewById(R.id.iv_createPhoto);//找到添加按钮控件
        photoList = queryFromDbAll();//数据库取到数据保存到photoList集合
        myAdapter.setData(photoList);//给myAdapter放入数据集合
        myAdapter.notifyDataSetChanged();//刷新
        recyclerView.setAdapter(myAdapter);
        re_opt = findViewById(R.id.re_opt);
        tv_add = findViewById(R.id.tv_add);
        tv_delete = findViewById(R.id.tv_delete);
        checkBox = findViewById(R.id.cb_selectAll);
    }

    //添加相册方法
    public void createPhoto() {
        //创建添加相册的弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_edittext, null);
        final EditText editText = (EditText) view.findViewById(R.id.et_account);//拿到弹窗输入框
        builder.setTitle("创建相册")
                .setMessage("是否创建相册")
                .setView(view)//设置自定义布局
                //确定按钮的点击事件
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取输入框内容
                        String str = editText.getText().toString().trim();
                        if (TextUtils.isEmpty(str)) {
                            Toast.makeText(Photo.this, "相册名不能为空", Toast.LENGTH_SHORT).show();
                        } else {
//                            Log.e("========111", str);
//                            //edittext 数据存储到SQLite
//                            values.put("name", str);
//                            db.insert(TABLE_NAME_STUDENT, null, values);
                            insert(str);//输入框内容存储到数据库
                            myAdapter.setData(photoList);
                            myAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(myAdapter);
                        }

                    }
                })
                //设置取消按钮的点击事件
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                //弹窗消失的监听事件
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //拿出sqlite数据显示
                        //List<PhotoBean> photoList2 = new ArrayList();
                        photoList = queryFromDbAll();
                        /*for(int i =0;i<photoList.size();i++){
                            for (int j=1;j<photoList.size()-1;j++){
                                if(photoList.get(i).getPhotoName().equals(photoList.get(j).getPhotoName())){
                                    continue;
                                } else{
                                    photoList2.add(photoList.get(i));
                                }
                            }
                        }*/
                        // listChangeSet(photoList);
                        myAdapter.notifyDataSetChanged();

                    }
                })
                .create();
        builder.show();
    }

    //连按两次返回键退出程序
    @Override
    public void onBackPressed() {
        if (!isQuit) {
            Toast.makeText(Photo.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            isQuit = true;
            //在两秒钟之后isQuit变成false
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);//休眠两秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        isQuit = false;
                    }
                }
            }).start();
        } else {
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //回调函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("=======777", "onActivityResult: " + requestCode + "=====" + resultCode);
        if (resultCode == RESULT_OK) {
            Log.e("=======666", "onActivityResult: " + data.getData().toString());
            PicBean picBean = new PicBean();
            picBean.setImgPath((data.getData().toString()));
            choosePicPath.add(picBean);
            if (choosePicPath.size() > 0) {
                photoList.get(0).setImgPath(choosePicPath.get(0).getImgPath());
            }
            valuesPic.put("path", data.getData().toString());
            dbPic.insert(TABLE_NAME_PIC, null, valuesPic);
            Log.e("=======eeeeee", "onActivityResult: " + choosePicPath.size());
            Log.e("=======hhhh", "onActivityResult: " + data.getData().toString());
            myAdapter.notifyDataSetChanged();
        }
    }

    //数据库查询所有
    public List<PhotoBean> queryFromDbAll() {
        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                PhotoBean photoBean = new PhotoBean();
                photoBean.setPhotoName(name);
                photoList.add(photoBean);
            }
            cursor.close();
        }
        return photoList;
    }

    //数据库查询名字
    public List<PhotoBean> queryFromDbByName(String name) {
        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, "name like ?", new String[]{name}, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex("name"));
                PhotoBean photoBean = new PhotoBean();
                photoBean.setPhotoName(name);
                photoList.add(photoBean);
            }
            cursor.close();
        }
        return photoList;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public void getDeleteData(List<PhotoBean> deleteData, int position) {
        deleteDataList = deleteData;
        Log.e("=============333KKKK", deleteDataList.size() + " " + deleteDataList.get(position).getPhotoName());
    }

    @Override
    public void onItemClick(int position) {
        if (choosePicPath.size() > 0) {
            Log.e("========vvv", choosePicPath + "");
            //如果图片集合里的数据个数大于0，就跳转到相册里
            Intent intent = new Intent(Photo.this, AlbumActivity.class);
            //intent.putParcelableArrayListExtra("picList",choosePicPath);
            startActivity(intent);
        } else {
            //跳转到手机系统相册
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
            intent.setType("image/*");
            startActivityForResult(intent, 2); // 打开相册
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SelectEvent event) {

        int size = event.getSize();
        if (size < list.size()) {
            isChange = true;
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
            isChange = false;
        }
        myAdapter.notifyDataSetChanged();

       /* Log.e("=======YYYY", event.message);
        Log.e("=======YYYY222", event.getPicList().size() + "");
        choosePicPath = event.getPicList();
        Log.e("=======YYYY333", choosePicPath.size() + "");
        if (choosePicPath.size() == 0) {
            photoList.get(0).setImgPath("");
        }
        myAdapter.setData(photoList);
        myAdapter.notifyDataSetChanged();*/
    }

    public void insert(String str) {
        PhotoBean photoBean = new PhotoBean();
        photoBean.setPhotoName(str);
        photoList.add(photoBean);

        //插入数据库中
        long rowId = helper.insertData(photoBean);
        if (rowId != -1) {
            Toast.makeText(getApplicationContext(), "创建成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "创建失败", Toast.LENGTH_SHORT).show();
        }

    }
}






















