package com.example.pagertitlestripdemo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> mViewList;//存放布局的集合
    private ArrayList<String> mTitleList;//存放标题的集合
    public MyPagerAdapter(){}

    public MyPagerAdapter(ArrayList<View> mViewList, ArrayList<String> mTitleList) {
        this.mViewList = mViewList;
        this.mTitleList = mTitleList;
    }

    @Override
    public int getCount() {
        return mViewList.size();//返回要滑动的view的个数
    }

    @Override//判断ViewPager中的view和instantiateItem方法返回的object是否相等
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override//将当前view添加到container里，返回当前view
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override//从container中删除指定的view
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
























