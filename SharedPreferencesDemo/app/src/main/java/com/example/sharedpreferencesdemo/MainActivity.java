package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2;
    EditText editText;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        editText = findViewById(R.id.et);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                String s = editText.getText().toString();
                saveData(s);
                break;
            case R.id.btn2:
                String content = sp.getString("content", "");
                Toast.makeText(this, "欢迎光临：" + content, Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void saveData(String string){
        SharedPreferences.Editor edit = sp.edit();//获取编辑器
        edit.putString("content",string);//写入数据
        edit.commit();//提交数据
    }
}