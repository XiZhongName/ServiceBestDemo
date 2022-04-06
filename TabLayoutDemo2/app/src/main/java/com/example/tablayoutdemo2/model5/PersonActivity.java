package com.example.tablayoutdemo2.model5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.tablayoutdemo2.R;
import com.example.tablayoutdemo2.adapter.GoodsFragmentAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity {
    private ImageView iv_bg;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    final ArrayList<Fragment> fragments = new ArrayList<>();
    final ArrayList<String> tabName = new ArrayList<>();
    String[] titleArray = {"动态","博客","分类专栏"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        iv_bg = findViewById(R.id.iv_bg);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        appBarLayout = findViewById(R.id.appbar_layout);
        tabLayout = findViewById(R.id.tab_layout5);
        viewPager = findViewById(R.id.view_pager5);
        //拿到初始图
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.smallimg);
        //处理得到模糊效果的图
        Bitmap blurBitmap = ImageFilter.blurBitmap(this,bmp,25f);
        iv_bg.setImageBitmap(blurBitmap);
        //伸缩偏移量监听
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){//收缩时
                    collapsingToolbarLayout.setTitle("告白气球");
                    isShow = true;
                } else if (isShow){//展开时
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

        for (int i = 0; i < titleArray.length; i++) {
            fragments.add(new PersonFragment(titleArray[i]));
            tabName.add(titleArray[i]);
        }
        GoodsFragmentAdapter fragmentAdapter = new GoodsFragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments,tabName);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

















































