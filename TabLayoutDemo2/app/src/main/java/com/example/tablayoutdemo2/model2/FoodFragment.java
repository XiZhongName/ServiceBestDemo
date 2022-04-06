package com.example.tablayoutdemo2.model2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tablayoutdemo2.R;



public class FoodFragment extends Fragment {
    public static FoodFragment newInstance(String label) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString("label", label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //给fragment页面的TextView设置文字
        String label = getArguments().getString("label");
        TextView text = getView().findViewById(R.id.tv_food);
        text.setText(label);
    }
}






















