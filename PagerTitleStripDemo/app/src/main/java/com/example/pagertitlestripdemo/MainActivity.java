package com.example.pagertitlestripdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<View> mViews;
    private ArrayList<String> mTitle;
    private View view1,view2,view3;
    private MyPagerAdapter myPagerAdapter;
    private LayoutInflater inflater;//声明布局容器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewpager);
        inflater = getLayoutInflater();//实例化布局容器
        view1 = inflater.inflate(R.layout.layout1,null);
        view2 = inflater.inflate(R.layout.layout2,null);
        view3 = inflater.inflate(R.layout.layout3,null);
        mViews = new ArrayList<View>();//把要显示的布局存放到集合里
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mTitle = new ArrayList<String>();//把要显示的标题放到集合里
        mTitle.add("推荐");
        mTitle.add("热门");
        mTitle.add("直播");
        myPagerAdapter = new MyPagerAdapter(mViews,mTitle);
        mViewPager.setAdapter(myPagerAdapter);//设置适配器
    }
}























