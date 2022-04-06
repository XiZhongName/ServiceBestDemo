package com.example.tablayoutdemo2.model1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tablayoutdemo2.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

public class BasicUseActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private String[] titles = {"one","two","three","four","five"};//标题
    private Integer[] icons = {R.drawable.ic_baseline_home,R.drawable.ic_baseline_insert_emoticon_24,
    R.drawable.ic_baseline_keyboard_voice_24,R.drawable.ic_baseline_live_tv_24,R.drawable.ic_baseline_local_police_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use);
        tabLayout = findViewById(R.id.tab_layout);
/*        只设置标题
        for (String title : titles){
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }*/

        //设置标题和图标
        for (int i = 0; i < titles.length; i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]).setIcon(icons[i]));
        }

        tabLayout.getTabAt(0).setText("ONE");//第一次进入页面时第一个标题是大写
        //标签选中监听
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中时设为大写
                tab.setText(titles[tab.getPosition()].toUpperCase());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中时设为小写
                tab.setText(titles[tab.getPosition()].toLowerCase());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //重新选中
                tab.setText(titles[tab.getPosition()].toUpperCase());
            }
        });
    }
}