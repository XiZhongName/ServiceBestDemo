package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText userName,password,content;
    DatabaseHelper helper;
    SQLiteDatabase db;
    String uname = null;
    String upwd = null;
    String sql;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.et_name);
        password = findViewById(R.id.et_pwd);
        content = findViewById(R.id.et_content);
        helper = new DatabaseHelper(this,"demo.db",null,1);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public void btnClick(View view) {
        uname = userName.getText().toString();
        upwd = password.getText().toString();
        switch (view.getId()){
            case R.id.btn1://创建
                sql = "insert into user(username,userpwd) values('"+uname+"','"+upwd+"')";
                db.execSQL(sql);
                userName.setText("");
                password.setText("");
                Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2://更新

                break;
            case R.id.btn3://查询
                sql = "select * from user";
                String[] args = null;
                if (!uname.equals("all")){
                    sql = sql + " where username=?";
                    args = new String[]{uname};
                    if (upwd != null && !upwd.equals("")){
                        sql = sql + " and userpwd=?";
                        args = new String[]{uname,upwd};
                    }
                } else {
                    args = null;
                }
                cursor = db.rawQuery(sql,args);
                cursor.moveToFirst();
                String name = null;
                String pwd = null;
                while (!cursor.isAfterLast()){
                    name = cursor.getString(cursor.getColumnIndex("username"));
                    pwd = cursor.getString(cursor.getColumnIndex("userpwd"));
                    cursor.moveToNext();
                }
                Toast.makeText(this, "查询完成", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn4://删除

                break;
        }
    }
}