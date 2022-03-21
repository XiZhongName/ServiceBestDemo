package com.example.logintest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_REGISTER = 1;//请求码
    public static final int INT = 0;//请求码
    private Button btnLogin;//登录按钮
    private EditText etAccount,etPassword;//账号输入框和密码输入框
    private String userName = "huizhong";
    private String pass = "123456";
    private TextView textView;//还没有账号
    private CheckBox cbRemember;//记住密码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("登录");//修改系统默认标题栏

        initView();//绑定view
        initData();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(MainActivity.this,"还没有注册账号！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.equals(userName,account)){//比较输入的账号和指定的账号是否相等
                    if (TextUtils.equals(pass,password)){//比较输入的密码和指定的密码是否相等
                        Toast.makeText(MainActivity.this,"恭喜你，登录成功！",Toast.LENGTH_LONG).show();
                        if (cbRemember.isChecked()){
                            //登录成功，记住密码后存储账号密码
                            SharedPreferences spf = getSharedPreferences("spfRecord",MODE_PRIVATE);
                            SharedPreferences.Editor edit = spf.edit();//存放数据
                            edit.putString("account",account);
                            edit.putString("password",password);
                            edit.putBoolean("isRemember",true);
                            edit.apply();
                        } else {
                            SharedPreferences spf = getSharedPreferences("spfRecord",MODE_PRIVATE);
                            SharedPreferences.Editor edit = spf.edit();
                            edit.putBoolean("isRemember",false);
                            edit.apply();
                        }
                        //登录后跳转页面，把账号传递给新页面
                        Intent intent = new Intent(MainActivity.this,UserProfileActivity.class);
                        intent.putExtra("account",account);
                        startActivity(intent);
                        MainActivity.this.finish();//结束当前页面
                    } else {
                        Toast.makeText(MainActivity.this,"密码不正确！",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,"用户名不正确！",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
            //把注册好的数据取出给登录页面的输入框
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER && resultCode == INT && data != null){
            Bundle extras = data.getExtras();
            String account = extras.getString("account");
            String password = extras.getString("password");
            etAccount.setText(account);
            etPassword.setText(password);
            userName = account;
            pass = password;
        }
    }

    //记住密码后，取数据给登陆页面
    private void initData() {
        SharedPreferences spf = getSharedPreferences("spfRecord",MODE_PRIVATE);
        boolean isRemember = spf.getBoolean("isRemember", false);
        String account = spf.getString("account", "");
        String password = spf.getString("password", "");
        if (isRemember){
            etAccount.setText(account);
            etPassword.setText(password);
            cbRemember.setChecked(true);
        }
        userName = account;
        pass = password;

    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        textView = findViewById(R.id.text_view);
        cbRemember = findViewById(R.id.cb_remember);
    }

    public void jumpToRegister(View view) {
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}