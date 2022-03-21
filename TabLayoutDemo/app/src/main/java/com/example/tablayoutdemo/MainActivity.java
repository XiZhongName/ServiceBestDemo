package com.example.tablayoutdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] tabs = {"tab1","tab2","tab3"};
    private List<TabFragment> tabFragmentList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        //添加tab
        for (int i = 0; i < tabs.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
            tabFragmentList.add(TabFragment.newInstance(tabs[i]));
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return tabFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return tabFragmentList.size();
            }
            //设置tab标题，一定要重写getPageTitle方法，不然标题显示不出来
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager,false);
        //设置点击后改变标题字体颜色和风格
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                View customView = tab.getCustomView();
//                if (customView == null){
//                    tab.setCustomView(R.layout.tab_text_layout);
//                }
//                TextView textView = tab.getCustomView().findViewById(R.id.text5);
//                textView.setTextAppearance(MainActivity.this,R.style.TabLayoutTextSelected);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                View customView = tab.getCustomView();
//                if (customView == null){
//                    tab.setCustomView(R.layout.tab_text_layout);
//
//                }
//                TextView textView = tab.getCustomView().findViewById(R.id.text5);
//                textView.setTextAppearance(MainActivity.this,R.style.TabLayoutTextUnSelected);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
}




























