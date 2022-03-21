package com.example.menutest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
        actionBarconfig();
    }

    private void actionBarconfig() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("弹出式菜单");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setLogo(R.drawable.ic_baseline_help_24);
        supportActionBar.setDisplayUseLogoEnabled(true);
    }

    public void popUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setGravity(Gravity.RIGHT);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                Toast.makeText(PopupMenuActivity.this, "你点击了"+item.getTitle(), Toast.LENGTH_SHORT).show();
                switch (itemId){
                    case R.id.menu_exit:
                        //Toast.makeText(this,"点击了退出",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_help:
                        // Toast.makeText(this,"点击了帮助",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_home:
                        //Toast.makeText(this,"点击了主页",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        // Toast.makeText(this,"点击了设置",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        //Toast.makeText(this,"点击了关于",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}