package com.example.tablayoutdemo2.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tablayoutdemo2.R;

import java.util.List;

public class PersonTypeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PersonTypeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_title_person,item)
                .setText(R.id.tv_title_content,item + "内容");
    }
}
