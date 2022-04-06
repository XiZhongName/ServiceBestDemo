package com.example.viewpagerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private List<View> mViews;//存放视图的数组
    private View view1, view2, view3;
    private PagerAdapter mPagerAdapter;//适配器
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewpager);
        inflater = getLayoutInflater();//获取布局对象管理
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        mViews = new ArrayList<View>();//把要显示的布局存放到数组里
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mPagerAdapter = new PagerAdapter() {
            @Override//返回要滑动的view的个数
            public int getCount() {
                return mViews.size();
            }

            @Override//判断pager的一个view是否和instantiateItem方法返回的object是否有关联
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override//从当前container中删除指定位置的view
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
               // super.destroyItem(container, position, object);一定要注释掉，否则会报错内存益处
                container.removeView(mViews.get(position));
            }

            @NonNull
            @Override//将当前视图添加到container中，返回当前view
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mViews.get(position));
                return mViews.get(position);
            }
        };
        mViewPager.setAdapter(mPagerAdapter);

    }
}



















