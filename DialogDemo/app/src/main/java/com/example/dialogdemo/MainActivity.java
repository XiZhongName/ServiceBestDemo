package com.example.dialogdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showNormalDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("你确定要退出程序吗？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public void myClick(View view){
        switch (view.getId()){
            case R.id.normal_dialog_btn:
                //1.实例化一个Builder
                //AlertDialog的构造方法是protected
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //设置对话框样式
                builder.setTitle("提示");//设置对话框标题
                builder.setMessage("你确定要退出吗");//提示语句
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消",null);
                //展示
                builder.show();//等价于下行代码
                // AlertDialog dialog = builder.create();
               // dialog.show();
                break;
            case R.id.diy_dialog_btn:
                MyDialog md = new MyDialog(this,R.style.mydialog);
                md.show();
                break;
            case R.id.popup_btn:
                showPopupWindow(view);
                break;
            case R.id.array_btn:
                showArrayDialog();
                break;
            case R.id.search_btn:

        }
    }

    private void showArrayDialog(){
        final String[] items = {"java","Mysql","Android","HTML","C","JavaScript"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
                .setAdapter(adapter,null);
        builder.show();
    }

    public void showPopupWindow(View view){
        //准备所需要的视图对象
        View v = LayoutInflater.from(this).inflate(R.layout.popup_layout,null);
        //1.实例化对象
        //参数1：用在弹窗中的view
        //参数2，3：弹窗的宽，高
        //参数4：（focusable）是否获取焦点
        PopupWindow window = new PopupWindow(v,700,200,true);
        //2.设置背景，动画
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //设置能响应外部的点击事件
        window.setOutsideTouchable(true);
        //设置弹窗能响应点击事件
        window.setTouchable(true);
        //为弹窗设置动画
        window.setAnimationStyle(R.style.translate_anim);
        //3.显示
        //参数1：anchor锚，参数2，3：在x,y轴上的偏移量
        window.showAsDropDown(view,-190,10);
        //为弹窗中的文本添加点击事件
        v.findViewById(R.id.choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了选择",Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.allin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了全选",Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"你点击了复制",Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
    }

}