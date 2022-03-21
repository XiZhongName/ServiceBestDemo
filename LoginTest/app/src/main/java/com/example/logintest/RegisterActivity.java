package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RESULT_CODE = 0;
    private Button register;//注册按钮
    private EditText et_Account, et_Password, et_Password2;//账号输入框，密码输入框
    private CheckBox cbagree;//勾选用户协议

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("注册");
        register = findViewById(R.id.register_btn);
        et_Account = findViewById(R.id.et_account1);
        et_Password = findViewById(R.id.et_password1);
        et_Password2 = findViewById(R.id.et_password2);
        cbagree = findViewById(R.id.cb_agree);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String name = et_Account.getText().toString();
        String pass = et_Password.getText().toString();
        String pass2 = et_Password2.getText().toString();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(pass)){
            Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_LONG).show();
        } else if (!TextUtils.equals(pass,pass2)){
            Toast.makeText(RegisterActivity.this,"两次输入的密码不一致，请确认！",Toast.LENGTH_LONG).show();
        } else if (!cbagree.isChecked()){
            Toast.makeText(RegisterActivity.this,"请同意用户协议",Toast.LENGTH_LONG).show();
        } else {
           //存储注册的账号和密码
            SharedPreferences spf = getSharedPreferences("spfRecord",MODE_PRIVATE);
            SharedPreferences.Editor edit = spf.edit();
            edit.putString("account",name);
            edit.putString("password",pass2);
            //数据回传给登录页面的输入框
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("account",name);
            bundle.putString("password",pass2);
            intent.putExtras(bundle);
            setResult(RESULT_CODE,intent);
            Toast.makeText(RegisterActivity.this,"恭喜你注册成功！",Toast.LENGTH_LONG).show();
            this.finish();
        }


    }


}