package com.example.fragmenttoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button2);
        //1.获取FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        //2.获取FragmentTransaction
        FragmentTransaction transaction = manager.beginTransaction();
        //3.创建fragment实例
        final BlankFragment blankFragment = new BlankFragment();
        //4.把fragment布局添加到Activity里
        transaction.add(R.id.frame_layout,blankFragment);
        transaction.commit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blankFragment.sendMessage(new MyCallBack() {
                    @Override
                    public void getMessageFromFragment(String string) {
                        textView.setText(string);
                    }
                });
            }
        });
    }
}