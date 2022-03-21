package com.example.dialogtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpPop(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("你确定要吃药吗");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定",null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void jumpPopActivity(View view) {
        Intent intent = new Intent(this,DialogActivity.class);
        startActivity(intent);

    }
}