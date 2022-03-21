package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitetest.util.ToastUtil;

public class DeleteActivity extends AppCompatActivity {
    private EditText etName;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        etName = findViewById(R.id.et_name2);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    public void delete(View view) {
        String name = etName.getText().toString().trim();

        //按姓名从数据库中删除
        int row = mySQLiteOpenHelper.deleteFromDbByName(name);
        if (row > 0){
            ToastUtil.toastLong(this,"删除成功，删除了" + row + "条数据");
        } else {
            ToastUtil.toastLong(this,"删除失败，没有找到符合条件的条数据");
        }

    }
}