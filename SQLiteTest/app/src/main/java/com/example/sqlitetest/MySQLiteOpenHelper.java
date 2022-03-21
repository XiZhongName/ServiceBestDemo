package com.example.sqlitetest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitetest.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DN_NAME = "mySQLite.db";//定义数据库的常量名字
    private static final String TABLE_NAME_STUDENT = "student";//定义数据里表的常量名字

    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_STUDENT + "(id integer primary key autoincrement,name text,number text,gender text,score text)";

    //重写父类方法，创建数据库
    public MySQLiteOpenHelper(Context context){
        super(context,DN_NAME,null,1);
    }



    @Override//创建数据库里的表
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);//SQL执行语句
    }

    @Override//数据库升级
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int deleteFromDbByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_STUDENT,"name like ?",new String[] {name});
    }

    public long insertData(Student student){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("number",student.getNumber());
        values.put("gender",student.getGender());
        values.put("score",student.getScore());
        return db.insert(TABLE_NAME_STUDENT,null,values);


    }

    public List<Student> queryFromDbByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, "name like ?", new String[]{name}, null,null,null);

        if (cursor != null){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex("number"));
                @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex("gender"));
                @SuppressLint("Range") String score = cursor.getString(cursor.getColumnIndex("score"));

                Student student = new Student();
                student.setName(name1);
                student.setNumber(number);
                student.setGender(gender);
                student.setScore(score);
                studentList.add(student);
            }
            cursor.close();
        }
        return studentList;
    }

    public List<Student> queryFromDbAll(){
        SQLiteDatabase db = getWritableDatabase();
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, null, null, null,null,null);

        if (cursor != null){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex("number"));
                @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex("gender"));
                @SuppressLint("Range") String score = cursor.getString(cursor.getColumnIndex("score"));

                Student student = new Student();
                student.setName(name1);
                student.setNumber(number);
                student.setGender(gender);
                student.setScore(score);
                studentList.add(student);
            }
            cursor.close();
        }
        return studentList;
    }

    public int  updateData(Student student){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("number",student.getNumber());
        values.put("gender",student.getGender());
        values.put("score",student.getScore());
        return db.update(TABLE_NAME_STUDENT,values,"name like ?",new String[]{student.getName()});

    }
}



















