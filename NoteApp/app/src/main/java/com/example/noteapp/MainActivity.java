package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.noteapp.adapter.MyAdapter;
import com.example.noteapp.bean.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private List<Note> mNotes;
    private MyAdapter mMyAdapter;
    private NoteDbOpenHelper mNoteDbOpenHelper;
    public static final int MODE_LINEAR = 0;
    public static final int MODE_GRID = 0;
    private int currentListLayoutMode = MODE_LINEAR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rlv);
        mNotes = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//
//            Note note = new Note();
//            note.setTitle("这是标题" + i);
//            note.setContent("这是内容" + i);
//            note.setCreatedTime(getCurrentTimeFormat());
//            mNotes.add(note);
//        }
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);
        mMyAdapter = new MyAdapter(mNotes,this);
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mMyAdapter.setViewType(MyAdapter.TYPE_LINEAR_LAYOUT);


    }

    @Override
    protected void onResume() {
        //返回到主页面时可以把数据加载进来
        super.onResume();
        refreshDataFromDb();
    }

    private void refreshDataFromDb() {
        mNotes = getDataFromDB();
        mMyAdapter.refreshData(mNotes);
    }

    private List<Note> getDataFromDB(){
       return mNoteDbOpenHelper.queryFromDbAll();
    }

    private String getCurrentTimeFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public void add(View view) {
        Intent intent = new Intent(this,AddActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mNotes = mNoteDbOpenHelper.queryFromDbByTitle(newText);
                mMyAdapter.refreshData(mNotes);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_linear:
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(layoutManager);
                mMyAdapter.setViewType(MyAdapter.TYPE_LINEAR_LAYOUT);
                mMyAdapter.notifyDataSetChanged();
                currentListLayoutMode = MODE_LINEAR;
                return true;
            case R.id.menu_grid:
                GridLayoutManager GridlayoutManager = new GridLayoutManager(this,2);
                mRecyclerView.setLayoutManager(GridlayoutManager);
                mMyAdapter.setViewType(MyAdapter.TYPE_GRID_LAYOUT);
                mMyAdapter.notifyDataSetChanged();
                currentListLayoutMode = MODE_GRID;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (currentListLayoutMode == MODE_LINEAR){
            MenuItem item = menu.findItem(R.id.menu_linear);
            item.setChecked(true);
        } else {
            menu.findItem(R.id.menu_grid).setChecked(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}