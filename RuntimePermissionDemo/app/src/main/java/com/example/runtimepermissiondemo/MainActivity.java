package com.example.runtimepermissiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button makeCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeCall = findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkSelfPermission接收两个参数，第一个是上下文，第二个是权限名
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    //向用户申请授权
                    //requestPermissions方法接收3个参数，第一个是Activity的实例
                    //第二个参数是一个string数组，把要申请的权限名放入数组
                    //第三个参数是请求码，只要是唯一值就可以了
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                            Manifest.permission.CALL_PHONE,Manifest.permission.PROCESS_OUTGOING_CALLS
                    },1);//申请权限的结果最终都会回调到onRequestPermissionsResult方法中
                } else {
                    call();
                }
            }
        });
    }
    private void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //授权的结果会封装到grantResults里
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                } else {
                    Toast.makeText(this, "你没有权限，需要申请权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}






















