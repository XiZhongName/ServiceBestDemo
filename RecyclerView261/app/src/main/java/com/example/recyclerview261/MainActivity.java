package com.example.recyclerview261;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.recyclerview261.Adapter.MyAdapter;
import com.example.recyclerview261.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private List<ItemBean> mItemBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mMyAdapter = new MyAdapter(this,mItemBean);
        mRecyclerView.setAdapter(mMyAdapter);
        //线性布局
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        //网格布局
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //瀑布流布局
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initData() {
        mItemBean = new ArrayList<>();
        ItemBean itemBean = new ItemBean();
        itemBean.setTitle("雨中漫步");
        itemBean.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean.setImgResId(R.drawable.img5);
        ItemBean itemBean2 = new ItemBean();
        itemBean2.setTitle("雨中漫步");
        itemBean2.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean2.setImgResId(R.drawable.img4);
        ItemBean itemBean3 = new ItemBean();
        itemBean3.setTitle("雨中漫步");
        itemBean3.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean3.setImgResId(R.drawable.img3);
        ItemBean itemBean4 = new ItemBean();
        itemBean4.setTitle("雨中漫步");
        itemBean4.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean4.setImgResId(R.drawable.img2);
        ItemBean itemBean5 = new ItemBean();
        itemBean5.setTitle("雨中漫步");
        itemBean5.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean5.setImgResId(R.drawable.img1);
        ItemBean itemBean6 = new ItemBean();
        itemBean6.setTitle("雨中漫步");
        itemBean6.setContent("人生就像一场旅行，不必在乎目的地，在乎的，是沿途的风景，以及看风景的心情。暮暮朝朝又一载，每个人都是匆匆的行者。");
        itemBean6.setImgResId(R.drawable.img6);

        mItemBean.add(itemBean);
        mItemBean.add(itemBean4);
        mItemBean.add(itemBean2);
        mItemBean.add(itemBean3);
        mItemBean.add(itemBean5);
        mItemBean.add(itemBean6);
        mItemBean.add(itemBean);
        mItemBean.add(itemBean4);
        mItemBean.add(itemBean2);
        mItemBean.add(itemBean3);
        mItemBean.add(itemBean5);
        mItemBean.add(itemBean);
        mItemBean.add(itemBean4);
        mItemBean.add(itemBean2);
        mItemBean.add(itemBean3);
        mItemBean.add(itemBean5);
        mItemBean.add(itemBean);
        mItemBean.add(itemBean4);
        mItemBean.add(itemBean2);
        mItemBean.add(itemBean3);
        mItemBean.add(itemBean5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_linear:
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

                mRecyclerView.setLayoutManager(layoutManager);
                return true;
            case R.id.menu_stagger:
                StaggeredGridLayoutManager StaggerLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(StaggerLayoutManager);

                return true;
            case R.id.menu_gird:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
                mRecyclerView.setLayoutManager(gridLayoutManager);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rlv);
    }
}