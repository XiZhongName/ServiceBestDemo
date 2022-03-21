package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapp.bean.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private EditText etTitle,etContent;
    private NoteDbOpenHelper mNoteDbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);
    }

    public void add(View view) {
        String title = etTitle.getText().toString();//得到输入框输入的内容
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(title)){//判断输入框是否为空
            Toast.makeText(this,"标题不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurrentTimeFormat());

        long row = mNoteDbOpenHelper.insertData(note);
        if (row != -1){
            Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
            this.finish();
        } else {
            Toast.makeText(this,"添加失败",Toast.LENGTH_SHORT).show();
        }
    }
    private String getCurrentTimeFormat(){//得到当前时间并把时间格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

}