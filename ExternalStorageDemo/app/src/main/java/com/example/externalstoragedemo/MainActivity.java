package com.example.externalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText userName,password,content;
    private String fileName = "hello_file.txt";
    //外部设备可读
    boolean mExternalStorageAvailable = false;
    //外部设备可写
    boolean mExternalStorageWriteable = false;

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
        //首先定义一个方法判断外部存储设备的状态
        checkExternalStorage();
        if (mExternalStorageWriteable){
            OutputStream out = new FileOutputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),fileName));
            out.write((name + "##" + password).getBytes());
            if (out != null){
                out.close();
            }
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }


    }

    private void checkExternalStorage() {
        String state = Environment.getExternalStorageState(); //得到外部存储器的状态
        if (Environment.MEDIA_MOUNTED.equals(state)){//外部存储卡可用的状态下可读也可写
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){//只可读
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            mExternalStorageAvailable = false;
            mExternalStorageWriteable = false;
        }
    }

    public void loadData(View view) throws IOException {
        checkExternalStorage();
        if (mExternalStorageAvailable){
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),fileName);
            if (file.exists()){
                FileInputStream inputStream = new FileInputStream(file);
                byte[] input = new byte[inputStream.available()];
                while (inputStream.read(input) != -1){
                    String s = new String(input);
                    String[] str = s.split("##");
                    content.setText("用户名:" + str[0] + "    密码：" + str[1] );
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }

        }

    }
}