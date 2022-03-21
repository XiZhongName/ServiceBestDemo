package com.example.transferdata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpActivity2(View view) {
        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra("userName","周杰伦");
        intent.putExtra("age",21);
        intent.putExtra("sing",true);
        startActivity(intent);
    }

    public void jumpActivity3(View view) {
        Intent intent = new Intent(this,Activity3.class);
        Bundle bundle = new Bundle();
        bundle.putString("userName","蔡依林");
        bundle.putInt("age",24);
        bundle.putChar("sex",'女');
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void jumpActivity4(View view) {
        Intent intent = new Intent(this,Activity4.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("userName","林俊杰");
        Bundle bundle2 = new Bundle();
        bundle2.putString("userName","梁静茹");
        intent.putExtra("people1",bundle1);
        intent.putExtra("people2",bundle2);
        startActivity(intent);
    }

    public void jumpActivity5(View view) {
        Intent intent = new Intent(this,Activity5.class);
        User user = new User();
        user.setName("五月天");
        user.setAge(22);
        user.setGender("男");
        user.setSing(true);
        intent.putExtra("user",(Serializable) user);
        startActivity(intent);
    }

    public void jumpActivity6(View view) {
        Intent intent = new Intent(this,Activity6.class);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1 && data != null){
            String result = data.getStringExtra("result");
            Toast.makeText(this,"带回的数据：" + result,Toast.LENGTH_LONG).show();
        }
    }
}