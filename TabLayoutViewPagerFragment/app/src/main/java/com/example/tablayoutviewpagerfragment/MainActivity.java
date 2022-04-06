package com.example.tablayoutviewpagerfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.tablayoutviewpagerfragment.adapter.MyAdapter;
import com.example.tablayoutviewpagerfragment.fragment.FoodFragment;
import com.example.tablayoutviewpagerfragment.fragment.MovieFragment;
import com.example.tablayoutviewpagerfragment.fragment.NewsFragment;
import com.example.tablayoutviewpagerfragment.fragment.ShopFragment;
import com.example.tablayoutviewpagerfragment.fragment.SportFragment;
import com.example.tablayoutviewpagerfragment.fragment.TVFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] tabs = {"美食", "电影", "新闻", "购物", "运动", "电视剧"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        //给TabLayout标题设置文字
        for (int i = 0; i < tabs.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));

        }
        //把tabs数组里的元素放入fragmentList集合里
        fragmentList.add(FoodFragment.newInstance(tabs[0]));
        fragmentList.add(MovieFragment.newInstance(tabs[1]));
        fragmentList.add(NewsFragment.newInstance(tabs[2]));
        fragmentList.add(ShopFragment.newInstance(tabs[3]));
        fragmentList.add(SportFragment.newInstance(tabs[4]));
        fragmentList.add(TVFragment.newInstance(tabs[5]));

        myAdapter = new MyAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, tabs);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager, false);//设置TabLayout和viewPager联动
        initTabLayout();//初始化TabLayout
    }

    private void initTabLayout() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {//getTabCount()得到当前选项卡的个数
            TabLayout.Tab tab = tabLayout.getTabAt(i);//得到指定位置的Tab
            if (tab != null) {
                //设置标签视图为TextView
                tab.setCustomView(getTabView(i));
            }
        }
        //第一次进入页面时默认选中标题的字体
        updateTabText(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()),true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override//选中时状态
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabText(tab,true);
            }

            @Override//未选中时状态
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabText(tab,false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void updateTabText(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect){
            //选中文字加大加粗,设置字体颜色
            TextView tabSelect = tab.getCustomView().findViewById(R.id.tv_title);
            tabSelect.setTextSize(20);
            tabSelect.setTextColor(Color.parseColor("#00ff00"));
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(R.id.tv_title);
            tabUnSelect.setTextSize(14);
            tabUnSelect.setTextColor(Color.BLACK);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
        }
    }

    private View getTabView(int currentPosition) {
        //获取tab_item布局
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item,null);
        TextView textView = view.findViewById(R.id.tv_title);//初始化tab_item布局里的TextView组件
        textView.setText(tabs[currentPosition]);
        return view;
    }

}

























