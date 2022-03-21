package com.example.photoalbum.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;

import com.example.photoalbum.R;
import com.example.photoalbum.bean.PhotoBean;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper2 extends SQLiteOpenHelper {
    private static final String DN_NAME = "mySQLite.db";//定义数据库的常量名字
    public static final String TABLE_NAME_STUDENT = "student";//定义数据里表的常量名字
    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_STUDENT + "(id integer primary key autoincrement,name text,number text,gender text,score text)";
    //重写父类方法，创建数据库
    public MySQLiteOpenHelper2(Context context){
        super(context,DN_NAME,null,1);
    }

    @Override//创建数据库里的表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);//SQL执行语句
    }

    @Override//数据库更新
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    public int deleteFromDbByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_STUDENT,"name like ?",new String[] {name});
    }

    public long insertData(PhotoBean photoBean){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",photoBean.getPhotoName());
        return db.insert(TABLE_NAME_STUDENT,null,values);
    }


    public List<PhotoBean> queryFromDbAll(){
        SQLiteDatabase db = getWritableDatabase();
        List<PhotoBean> studentList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, null, null, null,null,null);

        if (cursor != null){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex("name"));
                PhotoBean photoBean = new PhotoBean();
                photoBean.setPhotoName(name1);
                /*student.setNumber(number);
                student.setGender(gender);
                student.setScore(score);*/
                studentList.add(photoBean);
            }
            cursor.close();
        }
        return studentList;
    }
}



















