package com.example.greendaotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoSession = ((MyApp) getApplication()).getDaoSession();
        //添加数据
        Student student = new Student();
        student.name = "Bilibi";
        student.age = 19;
        daoSession.insert(student);
        Log.v("GreenDao", "插入数据" + student.toString());
        //输出数据
        List<Student> students = daoSession.loadAll(Student.class);
        Iterator<Student> iterator = students.iterator();
        for (Iterator<Student> it = iterator; it.hasNext(); ) {
            Student student1 = it.next();
            Log.v("GreenDao", "数据库数据：" + student1.name);
        }
    }
}