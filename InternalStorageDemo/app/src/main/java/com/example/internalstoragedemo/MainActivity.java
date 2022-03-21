package com.example.internalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText userName,password,content;
    private String fileName = "hello_file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.et_txt);
        password = findViewById(R.id.et_pwd);
        content = findViewById(R.id.et_content);
    }

    public void savaData(View view) throws IOException {
        String name = userName.getText().toString();
        String password = this.password.getText().toString();
        FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);//存储的文件名和模式
        fileOutputStream.write((name + "##" + password).getBytes());//##用于分割用户名和密码
        if (fileOutputStream != null){
            fileOutputStream.close();
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public void loadData(View view) throws IOException {
        FileInputStream fileInputStream = openFileInput(fileName);
        byte[] input = new byte[fileInputStream.available()];//判断文件有多少字节
        while (fileInputStream.read(input) != -1){//判断读取数组内容是不是最后一个
            String str = new String(input);
            String[] s = str.split("##");//分割用户名和密码
            content.setText("用户名：" + s[0] + "     密码:" + s[1]);
        }
        if (fileInputStream != null){
            fileInputStream.close();
        }
    }
}















