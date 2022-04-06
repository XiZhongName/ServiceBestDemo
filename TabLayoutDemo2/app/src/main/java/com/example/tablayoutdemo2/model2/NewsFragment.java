package com.example.tablayoutdemo2.model2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tablayoutdemo2.R;


public class NewsFragment extends Fragment {

    public static NewsFragment newInstance(String label) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("label",label);
        fragment.setArguments(args);//通过setArguments()方法设置Bundle类型参数
        return fragment;
    }
    //如果在创建Fragment时要传入参数，必须要通过setArguments(Bundle bundle)方式添加，
    // 而不建议通过为Fragment添加带参数的构造函数，因为通过setArguments()方式添加，
    // 在由于内存紧张导致Fragment被系统杀掉并恢复(re-instantiate)时能保留这些数据。

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //通过getArguments()方法取出传入的参数
        String label = getArguments().getString("label");
        TextView text = getView().findViewById(R.id.tv_news);
        text.setText(label);
    }
}