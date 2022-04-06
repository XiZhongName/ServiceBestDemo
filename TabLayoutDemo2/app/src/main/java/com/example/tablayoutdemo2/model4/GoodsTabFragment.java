package com.example.tablayoutdemo2.model4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tablayoutdemo2.R;


public class GoodsTabFragment extends Fragment {
    private TextView tvContent;
    private String content;

    public GoodsTabFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_tab, container, false);
        tvContent = view.findViewById(R.id.tv_content);
        return view;
    }
//onCreateView是创建的时候调用，onViewCreated是在onCreateView后被触发的事件，且onStart运行时间位于onViewCreated之后
    @Override//onViewCreated方法返回fragment要显示的布局view.
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvContent.setText(content + "详情");//修改商品详细内容的文字
    }
}



















