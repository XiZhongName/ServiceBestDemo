package com.example.myappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myappdemo.api.Api;
import com.example.myappdemo.api.ApiConfig;
import com.example.myappdemo.api.TtitCallback;
import com.example.myappdemo.util.AppConfig;
import com.example.myappdemo.util.StringUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    private EditText etAccount, etPwd;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                login(account, pwd);
            }
        });
    }
    private void login(String account,String password) {
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
        Api.config(ApiConfig.LOGIN,params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast(res);
//                    }
//                });
                navigateTo(HomeActivity.class);
                showToastSync(res);

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
//    private void login(String account,String password) {
//        if (StringUtils.isEmpty(account)){
//            showToast("请输入用户名");
//            return;
//        }
//        if (StringUtils.isEmpty(password)){
//            showToast("请输入密码");
//            return;
//        }
//        //第一步创建OKHttpClient
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        Map m = new HashMap();
//        m.put("mobile",account);
//        m.put("password",password);
//        JSONObject jsonObject = new JSONObject(m);
//        String jsonStr = jsonObject.toString();
//        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonStr);
//        //第三步创建Request
//        Request request = new Request.Builder()
//                .url(AppConfig.BASE_URL + "/app/login")
//                .addHeader("contentType","application/json;charset=utf-8")
//                .post(requestBodyJson)
//                .build();
//        //第四步创建call回调对象
//        final Call call = client.newCall(request);
//        //第五步发起请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("onFailure", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast(result);
//                    }
//                });
//
//            }
//        });
//
//    }
//}

























