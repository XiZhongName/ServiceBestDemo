package com.example.fragmentmanagers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button change,replace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change = findViewById(R.id.btn);
        replace = findViewById(R.id.btn2);
        change.setOnClickListener(this);
        replace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Bundle bundle = new Bundle();
                bundle.putString("message","我喜欢学习");
                BlankFragment1 bf = new BlankFragment1();
                bf.setArguments(bundle);
                bf.setFragmentCallback(new IFragmentCallback() {
                    @Override
                    public void sendMsgToActivity(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public String getMsgFromActivity(String msg) {
                        return "我是来自activity的消息";
                    }
                });
                replaceFragment(bf);
                break;
            case  R.id.btn2:
                replaceFragment(new ItemFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}





















