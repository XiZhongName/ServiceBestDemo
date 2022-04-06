package com.example.tablayoutviewpagerdemo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {

    public static TabFragment newInstance(String label) {
        Bundle args = new Bundle();
        args.putString("label",label);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        String label = getArguments().getString("label");
        TextView text = getView().findViewById(R.id.tv_bg);
        text.setText(label);
       /* text.setBackgroundColor(Color.rgb((int)(Math.random() * 255),
                (int)(Math.random() * 255), (int)(Math.random() * 255)));*/
    }
}




















