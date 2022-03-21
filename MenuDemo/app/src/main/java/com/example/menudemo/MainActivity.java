package com.example.menudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册
        //registerForContextMenu(findViewById(R.id.button));
        //创建 覆盖父类方法

        findViewById(R.id.button).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               startActionMode(cb);
                return false;
            }
        });
        //演示PopupMenu菜单
        final Button popupBtn = findViewById(R.id.button2);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化PopupMenu对象 参数2：被锚定的view
                PopupMenu menu = new PopupMenu(MainActivity.this,popupBtn);
                //加载菜单资源：利用MenuInflater将Menu资源加载到PopupMenu.getMenu()所返回的Menu对象中
                //将R.menu.xx对应的菜单资源加载到弹出式菜单中
                menu.getMenuInflater().inflate(R.menu.popup,menu.getMenu());
                //为PopupMenu设置点击监听器
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.copy:
                                Toast.makeText(MainActivity.this,"复制",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.paste:
                                Toast.makeText(MainActivity.this,"粘贴",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                // 千万不能忘这一步，显示这一步
                menu.show();
            }
        });
    }
    ActionMode.Callback cb = new ActionMode.Callback() {
        //创建，在启动上下文操作模式（startActionMode(CallBack)）时调用
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.e("TAG","创建");
            getMenuInflater().inflate(R.menu.context,menu);
            return true;
        }
        //在创建方法后调用
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e("TAG","准备");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.e("TAG","点击");
            switch (item.getItemId()){
                case R.id.delet:
                    Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.opera:
                    Toast.makeText(MainActivity.this,"操作1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.opera2:
                    Toast.makeText(MainActivity.this,"操作2",Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
        //上下文操作模式结束时调用
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.e("TAG","结束");
        }
    };
/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context,menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }


    //菜单项的操作 覆盖onContextItemSelected
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delet:
                Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();
                break;
            case R.id.opera:
                Toast.makeText(this,"操作1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.opera2:
                Toast.makeText(this,"操作2",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

 */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();//退出程序
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}