package com.example.duckdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_top);
        viewPager = findViewById(R.id.view_pager);
        ArrayList<String> arrayList = new ArrayList<>();
        //添加标题到arraylist
        arrayList.add("全部");
        arrayList.add("全部");
        arrayList.add("全部");
        arrayList.add("远程人才库");
        prepareViewPager(viewPager,arrayList);
        tabLayout.setupWithViewPager(viewPager);
        
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        MainFragment fragment = new MainFragment();
        for (int i = 0; i < arrayList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("title",arrayList.get(i));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment,arrayList.get(i));
            fragment = new MainFragment();
        }
        viewPager.setAdapter(adapter);
    }
}



























