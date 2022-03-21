package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlitetest.bean.Student;
import com.example.sqlitetest.util.ToastUtil;

import java.util.List;

public class QueryActivity extends AppCompatActivity {
    private EditText etName;
    private TextView tvResult;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        etName = findViewById(R.id.et_name2);
        tvResult = findViewById(R.id.tv_result);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    public void query(View view) {
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
           //查询所有数据
            List<Student> students = mySQLiteOpenHelper.queryFromDbAll();
            showData(students);
            return;
        }
        //按姓名从数据库中查询
        List<Student> students = mySQLiteOpenHelper.queryFromDbByName(name);
        showData(students);
    }

    private void showData(List<Student> students) {
        StringBuilder builder = new StringBuilder();
        for (Student stu:
             students) {
            builder.append("姓名：");
            builder.append(stu.getName());
            builder.append("  学号：");
            builder.append( stu.getNumber());
            builder.append("  性别：");
            builder.append(stu.getGender());
            builder.append( "  分数：");
            builder.append(stu.getScore());
            builder.append("\n");
        }
        tvResult.setText(builder);
    }
}















