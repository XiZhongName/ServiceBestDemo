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
    private CheckBox checkBox;//?????????
    private boolean isChange = false;
    private ArrayList<PhotoBean> list = new ArrayList<>();
    private EventBus eventBus;

    ContentValues values = new ContentValues();
    private List<PhotoBean> photoList = new ArrayList<>();
    LinearLayout ll_selectTitle;//??????
    RelativeLayout re_opt;//???????????????????????????
    TextView tv_add, tv_delete;//?????????????????????
    private int allSelect;//?????????????????????
    List<PhotoBean> deleteDataList;//?????????????????????
    List<PicBean> choosePicPath = new ArrayList<>();//??????????????????????????????
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
        //??????????????????????????????
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
                //??????????????????????????????????????????????????????????????????????????????
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
                //??????????????????????????????????????????????????????????????????????????????
                if (allSelect % 2 == 0) {
                    re_opt.setVisibility(View.GONE);
                    myAdapter.showCheckBox(false);
                } else {
                    re_opt.setVisibility(View.VISIBLE);
                    myAdapter.showCheckBox(true);
                }
            }
        });*/



        //???????????????????????????
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPhoto();//????????????
            }
        });
        //???????????????????????????
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < deleteDataList.size(); i++) {
                    helper.deleteFromDbByName(deleteDataList.get(i).getPhotoName());
                }
                photoList.removeAll(deleteDataList);
                myAdapter.setData(photoList);
                myAdapter.notifyDataSetChanged();//??????recyclerView
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
        myAdapter = new MyAdapter(Photo.this);//?????????
        //myAdapter.setDeleteCheckBox(this);
        myAdapter.setmListener(this);
        recyclerView = findViewById(R.id.recyclerview_photo);
        createPhotoButton = findViewById(R.id.iv_createPhoto);//????????????????????????
        photoList = queryFromDbAll();//??????????????????????????????photoList??????
        myAdapter.setData(photoList);//???myAdapter??????????????????
        myAdapter.notifyDataSetChanged();//??????
        recyclerView.setAdapter(myAdapter);
        re_opt = findViewById(R.id.re_opt);
        tv_add = findViewById(R.id.tv_add);
        tv_delete = findViewById(R.id.tv_delete);
        checkBox = findViewById(R.id.cb_selectAll);
    }

    //??????????????????
    public void createPhoto() {
        //???????????????????????????
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_edittext, null);
        final EditText editText = (EditText) view.findViewById(R.id.et_account);//?????????????????????
        builder.setTitle("????????????")
                .setMessage("??????????????????")
                .setView(view)//?????????????????????
                //???????????????????????????
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //?????????????????????
                        String str = editText.getText().toString().trim();
                        if (TextUtils.isEmpty(str)) {
                            Toast.makeText(Photo.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                        } else {
//                            Log.e("========111", str);
//                            //edittext ???????????????SQLite
//                            values.put("name", str);
//                            db.insert(TABLE_NAME_STUDENT, null, values);
                            insert(str);//?????????????????????????????????
                            myAdapter.setData(photoList);
                            myAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(myAdapter);
                        }

                    }
                })
                //?????????????????????????????????
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                //???????????????????????????
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //??????sqlite????????????
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

    //?????????????????????????????????
    @Override
    public void onBackPressed() {
        if (!isQuit) {
            Toast.makeText(Photo.this, "????????????????????????", Toast.LENGTH_SHORT).show();
            isQuit = true;
            //??????????????????isQuit??????false
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);//????????????
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

    //????????????
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

    //?????????????????????
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

    //?????????????????????
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
            //??????????????????????????????????????????0????????????????????????
            Intent intent = new Intent(Photo.this, AlbumActivity.class);
            //intent.putParcelableArrayListExtra("picList",choosePicPath);
            startActivity(intent);
        } else {
            //???????????????????????????
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            //Intent.ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT"
            intent.setType("image/*");
            startActivityForResult(intent, 2); // ????????????
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

        //??????????????????
        long rowId = helper.insertData(photoBean);
        if (rowId != -1) {
            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
        }

    }
}






















