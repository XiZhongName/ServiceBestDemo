package com.example.sharedpreferences21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = findViewById(R.id.ed_content);
    }
    //存数据
    public void save(View view) {
        String content = etContent.getText().toString();
        SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spRecord.edit();
        edit.putString("content",content);
        edit.commit();
        Toast.makeText(this,"已存储" + content,Toast.LENGTH_SHORT).show();
        etContent.setText("");
    }
    //取数据
    public void get(View view) {
        SharedPreferences spRecord = getSharedPreferences("spRecord", MODE_PRIVATE);
        String content = spRecord.getString("content", "");
        etContent.setText(content);
        etContent.setSelection(content.length());//将光标设置为内容后面
    }
}