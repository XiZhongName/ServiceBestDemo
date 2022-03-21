package com.example.fragmenttofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BlankFragment1 blankFragment1 = new BlankFragment1();
        BlankFragment2 blankFragment2 = new BlankFragment2();
        transaction.add(R.id.frame_layout1,blankFragment1);
        transaction.add(R.id.frame_layout2,blankFragment2);
        transaction.commit();
    }
    public void change(MyCallBack myCallBack){
        myCallBack.changeText("按钮");
    }
}