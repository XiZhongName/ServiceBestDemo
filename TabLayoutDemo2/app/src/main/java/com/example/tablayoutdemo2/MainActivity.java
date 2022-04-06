package com.example.tablayoutdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tablayoutdemo2.model1.BasicUseActivity;
import com.example.tablayoutdemo2.model2.ClassificationActivity;
import com.example.tablayoutdemo2.model3.HomeActivity;
import com.example.tablayoutdemo2.model4.GoodsActivity;
import com.example.tablayoutdemo2.model5.PersonActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_basic,btn_viewpager_fragment,btn_TabItem,btn_goods,btn_person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_basic = findViewById(R.id.btn_basic);
        btn_viewpager_fragment = findViewById(R.id.btn_viewpager_fragment);
        btn_TabItem = findViewById(R.id.btn_TabItem);
        btn_goods = findViewById(R.id.btn_goods);
        btn_person = findViewById(R.id.btn_person);
        btn_basic.setOnClickListener(this);
        btn_viewpager_fragment.setOnClickListener(this);
        btn_TabItem.setOnClickListener(this);
        btn_goods.setOnClickListener(this);
        btn_person.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_basic:
                Intent intent = new Intent(MainActivity.this, BasicUseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_viewpager_fragment:
                Intent intent2 = new Intent(MainActivity.this, ClassificationActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_TabItem:
                Intent intent3 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_goods:
                Intent intent4 = new Intent(MainActivity.this, GoodsActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_person:
                Intent intent5 = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(intent5);
                break;

        }
    }
}





















