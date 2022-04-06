package com.example.tablayoutviewpagerfragment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tablayoutviewpagerfragment.R;


public class MovieFragment extends Fragment {

    public static MovieFragment newInstance(String label) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString("label",label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        String label = getArguments().getString("label");
        TextView text = getView().findViewById(R.id.tv_movie);
        text.setText(label);
    }
}