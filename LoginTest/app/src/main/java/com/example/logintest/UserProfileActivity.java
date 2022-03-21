package com.example.logintest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {
    private TextView tvNickName,tvGender,tvAge,tvCity,tvAccount,tvBirthTime,tvHome,tvSchool,tvSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initView();
        initData();

    }

    private void initData() {
        getDataFromSpf();

    }

    private void getDataFromSpf() {
        SharedPreferences spfRecord = getSharedPreferences("spfRecord",MODE_PRIVATE);
        String account = spfRecord.getString("account", "");
        String nickName = spfRecord.getString("nick_name", "");
        String gender = spfRecord.getString("gender", "");
        String age = spfRecord.getString("age", "");
        String city = spfRecord.getString("city", "");
        String birthTime = spfRecord.getString("birth_time", "");
        String home = spfRecord.getString("home", "");
        String school = spfRecord.getString("school", "");
        String sign = spfRecord.getString("sign", "");

        tvNickName.setText(account);
        tvGender.setText(gender);
        tvAge.setText(age);
        tvCity.setText(city);
        tvAccount.setText(account);
        //tvBirthTime.setText(birthTime);
        tvHome.setText(home);
        tvSchool.setText(school);
        tvSign.setText(sign);

    }

    private void initView() {
        tvNickName = findViewById(R.id.tv_nick_name);
        tvGender = findViewById(R.id.tv_gender);
        tvAge = findViewById(R.id.tv_age);
        tvCity = findViewById(R.id.tv_city);
        tvAccount = findViewById(R.id.tv_account);
        tvBirthTime = findViewById(R.id.tv_birth_time);
        tvHome = findViewById(R.id.tv_home);
        tvSchool = findViewById(R.id.tv_school);
        tvSign = findViewById(R.id.tv_sign);

        Intent intent = getIntent();
        String account = intent.getStringExtra("account");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2 && data != null){
            Bundle extras = data.getExtras();
            String account = extras.getString("account");
            String city = extras.getString("city");
            tvCity.setText("city");
            tvSchool.setText("school");
        }
    }

    public void toEdit(View view) {
        Intent intent = new Intent(UserProfileActivity.this,EditProfileActivity.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        Intent intent = new Intent(UserProfileActivity.this,MainActivity.class);

        startActivity(intent);
        this.finish();
    }
}