package com.example.myappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myappdemo.api.Api;
import com.example.myappdemo.api.ApiConfig;
import com.example.myappdemo.api.TtitCallback;
import com.example.myappdemo.util.AppConfig;
import com.example.myappdemo.util.StringUtils;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {
    private EditText etAccount, etPwd;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                register(account, pwd);
            }
        });
    }

    private void register(String account,String password) {
        if (StringUtils.isEmpty(account)) {
            showToast("请输入用户名");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            showToast("请输入密码");
            return;
        }
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("mobile",account);
        params.put("password",password);
        Api.config(ApiConfig.REGISTER,params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(res);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("onFailure", e.toString());
            }
        });
    }
}