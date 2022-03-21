package com.example.menutest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toOptionsMenu(View view) {
        Intent intent = new Intent(this,OptionsMenu.class);
        startActivity(intent);
    }

    public void toContextMenu(View view) {
        Intent intent = new Intent(this,ContextMenuActivity.class);
        startActivity(intent);
    }

    public void toPopupMenu(View view) {
        Intent intent = new Intent(this,PopupMenuActivity.class);
        startActivity(intent);
    }
}