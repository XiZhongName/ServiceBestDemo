package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.sqlitetest.bean.Student;
import com.example.sqlitetest.util.ToastUtil;

public class UpdateActivity extends AppCompatActivity {
    private EditText etName,etScore,etNumber;
    private RadioButton rbMan,rbWoman;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etScore = findViewById(R.id.et_score);
        rbMan = findViewById(R.id.rb_boy);
        rbWoman = findViewById(R.id.rb_girl);
    }

    public void update(View view) {
        String name = etName.getText().toString().trim();//trim()去掉输入框里多余的空格类的东西
        String number = etNumber.getText().toString().trim();
        String score = etScore.getText().toString().trim();
        String gender = "";
        if (rbMan.isChecked()){
            gender = "男";
        }
        if (rbWoman.isChecked()){
            gender = "女";
        }
        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setScore(score);
        student.setNumber(number);
        //插入数据库中
        int rowId = mySQLiteOpenHelper.updateData(student);
        if (rowId > 0 ){
            ToastUtil.toastShort(this,"更新成功");
        } else {
            ToastUtil.toastShort(this,"没有数据被更新");
        }

    }
}