package com.example.activitytofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_text);
        //1.获取fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //2.获取FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //3.创建需要添加的Fragment
        final BlankFragment fragment = new BlankFragment();
        //4.创建Bundle对象
        Bundle bundle = new Bundle();
        bundle.putString("message","我喜欢写代码");
        //5.把数据设置到Fragment里
        fragment.setArguments(bundle);
        //6.动态添加fragment
        //把创建的fragment添加到Activity布局文件中定义的位置（FrameLayout）
        fragmentTransaction.add(R.id.frame_layout,fragment);
        //7.提交
        fragmentTransaction.commit();
    }
}






















