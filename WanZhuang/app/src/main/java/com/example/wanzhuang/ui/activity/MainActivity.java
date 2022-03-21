package com.example.wanzhuang.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wanzhuang.R;
import com.example.wanzhuang.ui.adapter.MFragmentPagerAdapter;
import com.example.wanzhuang.ui.fragment.FindFragment;
import com.example.wanzhuang.ui.fragment.HomeFragment;
import com.example.wanzhuang.ui.fragment.MyFragment;
import com.example.wanzhuang.ui.fragment.OrderFragment;
import com.example.wanzhuang.ui.fragment.WorkFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Fragment> fragmentArrayList;
    private FragmentManager fragmentManager;
    private LinearLayout home, order, work, find, my;
    // FragmentTransaction transaction;
    HomeFragment homeFragment;
    OrderFragment orderFragment;
    WorkFragment workFragment;
    FindFragment findFragment;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentArrayList = new ArrayList<Fragment>();
        homeFragment = new HomeFragment();
        orderFragment = new OrderFragment();
        workFragment = new WorkFragment();
        findFragment = new FindFragment();
        myFragment = new MyFragment();
        fragmentArrayList.add(homeFragment);
        fragmentArrayList.add(orderFragment);
        fragmentArrayList.add(workFragment);
        fragmentArrayList.add(findFragment);
        fragmentArrayList.add(myFragment);
        fragmentManager = getSupportFragmentManager();
//        transaction = getFragmentManager().beginTransaction();
        home = findViewById(R.id.layout_home);
        order = findViewById(R.id.layout_order);
        work = findViewById(R.id.layout_work);
        find = findViewById(R.id.layout_find);
        my = findViewById(R.id.layout_my);
        //添加点击事件


        home.setOnClickListener(new MyOnClickListener(0));
        order.setOnClickListener(new MyOnClickListener(1));
        work.setOnClickListener(new MyOnClickListener(2));
        find.setOnClickListener(new MyOnClickListener(3));
        my.setOnClickListener(new MyOnClickListener(4));

//        home.setOnClickListener(this);
//        order.setOnClickListener(this);
//        work.setOnClickListener(this);
//        find.setOnClickListener(this);
//        my.setOnClickListener(this);
        mViewPager = findViewById(R.id.viewpager);

        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));
        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(3);
        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //设置viewpager页面滑动监听事件
        //mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    //    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.layout_home:
//                fragmentManager.getFragment().
//                break;
//            case R.id.layout_order:
//
//                break;
//            case R.id.layout_work:
//
//                break;
//            case R.id.layout_find:
//
//                break;
//            case R.id.layout_my:
//
//                break;
//            default:
//                break;
//        }
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

}