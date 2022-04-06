package com.example.tablayoutdemo2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class GoodsFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> tabName;

    public GoodsFragmentAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> fragments, ArrayList<String> tabName) {
        super(fm, behavior);
        this.fragments = fragments;
        this.tabName = tabName;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabName.get(position);
    }
}

















