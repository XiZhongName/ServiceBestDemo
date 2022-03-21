package com.example.photoalbum.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoalbum.R;
import com.example.photoalbum.bean.User;
import com.example.photoalbum.util.MySqliteOpenHelper;
import com.example.photoalbum.util.SPUtils;

/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private EditText etAccount;//手机号
    private EditText etPassword;//密码
    private TextView tvRegister;//注册
    private Button btnLogin;//登录按钮
    private Switch btnSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new MySqliteOpenHelper(this);
        etAccount =(EditText) findViewById(R.id.et_account);//获取手机号
        etPassword=(EditText)findViewById(R.id.et_password);//获取密码
        tvRegister=(TextView)findViewById(R.id.tv_register);//获取注册
        btnLogin=(Button)findViewById(R.id.btn_login);//获取登录
        btnSwitch = findViewById(R.id.save_switch);
        SharedPreferences pref = LoginActivity.this.getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        etAccount.setText(pref.getString("account1",""));
        etPassword.setText(pref.getString("password",""));
        btnSwitch.setChecked(pref.getBoolean("switch",false));
        //手机号注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册页面
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String account = etAccount.getText().toString();
                    String password = etPassword.getText().toString();
                    editor.putString("account1",account);
                    editor.putString("password",password);
                }else {
                    editor.putString("account1","");
                    editor.putString("password","");
                }
                editor.putBoolean("switch",isChecked);
                editor.commit();
            }
        });
        //设置点击按钮
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭虚拟键盘
                InputMethodManager inputMethodManager= (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                //获取请求参数
                String account= etAccount.getText().toString();
                String password=etPassword.getText().toString();
                if ("".equals(account)){//账号不能为空
                    Toast.makeText(LoginActivity.this,"账号不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(password)){//密码为空
                    Toast.makeText(LoginActivity.this,"密码为空", Toast.LENGTH_LONG).show();
                    return;
                }
                User mUser = null;
                String sql = "select * from user where account = ?";
                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor cursor = db.rawQuery(sql, new String[]{account});
                if (cursor != null && cursor.getColumnCount() > 0) {
                    while (cursor.moveToNext()) {
                        String dbId = cursor.getString(0);
                        String dbAccount = cursor.getString(1);
                        String dbName = cursor.getString(2);
                        String dbPassword = cursor.getString(3);
                        String dbPhoto = cursor.getString(4);
                        String dbSex = cursor.getString(5);
                        mUser = new User(dbId, dbAccount,dbName,dbPassword,dbPhoto,dbSex);
                    }
                }
                db.close();
                if (mUser != null) {
                    if (!password.equals(mUser.getPassword())) {
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }else{
                        SPUtils.put(LoginActivity.this, SPUtils.ACCOUNT,account);
                        //登录成功，跳转到相册
                        Intent intent = new Intent(LoginActivity.this, Photo.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}