package com.example.tablayoutdemo2.model4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tablayoutdemo2.R;
import com.example.tablayoutdemo2.adapter.GoodsFragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.youngkaaa.yviewpager.YViewPager;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class GoodsActivity extends AppCompatActivity {
    private VerticalTabLayout verticalTabLayout;
    private YViewPager yViewPager;
    private List<String> titleList = new ArrayList<>();//TabLayout标题
    final ArrayList<String> tabName = new ArrayList<>();//商品详细内容的文字
    final ArrayList<Fragment> fragments = new ArrayList<>();
    private GoodsFragmentAdapter goodsFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        verticalTabLayout = findViewById(R.id.tab_layout4);
        yViewPager = findViewById(R.id.view_pager4);
        int num = new Random().nextInt(50);//50以内随机数
        Toast.makeText(this, "共有" + num + "个商品", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < num; i++) {
            titleList.add("商品" + (i + 1));
        }
        for (int i = 0; i < titleList.size(); i++) {
            fragments.add(new GoodsTabFragment(titleList.get(i)));
            tabName.add(titleList.get(i));
        }
        goodsFragmentAdapter = new GoodsFragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, tabName);
        yViewPager.setAdapter(goodsFragmentAdapter);
        verticalTabLayout.setupWithViewPager(yViewPager);
    }
}


















