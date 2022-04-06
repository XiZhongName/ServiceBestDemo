package com.example.tablayoutviewpagerfragment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tablayoutviewpagerfragment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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






















