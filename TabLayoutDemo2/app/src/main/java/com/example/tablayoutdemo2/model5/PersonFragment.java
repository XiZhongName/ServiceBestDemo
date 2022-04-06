package com.example.tablayoutdemo2.model5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tablayoutdemo2.R;
import com.example.tablayoutdemo2.adapter.PersonTypeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PersonFragment extends Fragment {
    private String content;
    private RecyclerView rv;
    private List<String> typeList = new ArrayList<>();

    public PersonFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        rv = view.findViewById(R.id.rv);//初始化fragment里的recyclerview
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int num = 10;
        typeList.clear();
        //给recyclerview里添加数据
        for (int i = 0; i < num; i++) {
            typeList.add(content + (i + 1) + "*");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        PersonTypeAdapter personTypeAdapter = new PersonTypeAdapter(R.layout.item_person_type_rv,typeList);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(personTypeAdapter);
    }
}





















