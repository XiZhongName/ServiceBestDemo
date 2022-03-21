package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
        Button queryButton = (Button)findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book: books) {
                    Log.d("MainActivity","书名字是"+book.getName());
                    Log.d("MainActivity","书作者是"+book.getAuthor());
                    Log.d("MainActivity","书的页数是"+book.getPages());
                    Log.d("MainActivity","书的价格是"+book.getPrice());
                    Log.d("MainActivity","书的出版社是"+book.getPress());
                }
            }
        });
        Button deleteButton = (Button)findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price < ?","15");
            }
        });
        Button updateData = (Button)findViewById(R.id.up_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
//                book.setName("三国演义");
//                book.setAuthor("罗贯中");
//                book.setPages(2099);
                book.setPrice(31.8);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?","三国演义","罗贯中");
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("钢铁是怎样炼成的");
                book.setAuthor("普京");
                book.setPages(1078);
                book.setPrice(20.98);
                book.setPress("Unknow");
                book.save();
            }
        });
    }
}