package com.example.tablayoutdemo2.model2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tablayoutdemo2.R;


public class ShopFragment extends Fragment {

    public static ShopFragment newInstance(String label) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString("label",label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        String label = getArguments().getString("label");
        TextView text = getView().findViewById(R.id.tv_shop);
        text.setText(label);
    }
}