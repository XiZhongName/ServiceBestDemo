package com.example.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etName,etNumber;
    private TextView tvContent;

    String mSelectionClause;//查询的限制条件
    String[] mSelectionArgs;//限制条件参数的具体内容
    String[] SQL_COLUMN = new String[]{  //查询的列
            ContactsContract.CommonDataKinds.Identity.RAW_CONTACT_ID,//raw_contact_id主键
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,//display_name 姓名
            ContactsContract.CommonDataKinds.Phone.NUMBER  //data1 电话号码
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.et_username);
        etNumber = findViewById(R.id.et_number);
        tvContent = findViewById(R.id.tv_content);

        //动态申请权限
        if (Build.VERSION.SDK_INT >= 23){
            String[] permissions = {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS};
            int i = 0;
            for(String permission : permissions){
                if (this.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(permissions,i++);
                }
            }
        }
    }

    public void queryData(View view) {
        String name = etName.getText().toString();
        if (!name.equals("")){
            Cursor cursor = getContact(name);
            printQueryResult(cursor);
            Toast.makeText(this, "已找到该用户", Toast.LENGTH_SHORT).show();
        }
    }

    private Cursor getContact(String mSearchString) {
        mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME + "=?";
        mSelectionArgs = new String[]{mSearchString};
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                SQL_COLUMN, mSelectionClause, mSelectionArgs, null);
        return cursor;
    }


    public void insertData(View view) {
        String name = etName.getText().toString();
        String phoneNum = etNumber.getText().toString();
        ContentValues values = new ContentValues();//用于存储数据
        Uri uri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);//得到可以插入的uri
        long parseId = ContentUris.parseId(uri);//解析上面得到的uri， 得到一个id
        if (!name.equals("")){
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID,parseId);
            values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
            if (!phoneNum.equals("")){
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID,parseId);
                values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                values.put(ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,phoneNum);
                getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
            }
            Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请检查输入内容不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteData(View view) {
        String name = etName.getText().toString();
        if (!name.equals("")){
            getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI,ContactsContract.Contacts.DISPLAY_NAME + "=?",new String[]{name});
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(View view) {
        String name = etName.getText().toString();
        String phoneNum = etNumber.getText().toString();
        Long rawContactId = 0L;
        Uri uri = Uri.parse("content://com.android.contacts/data");
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("data1",phoneNum);
        if (!name.equals("")){
            Cursor cursor = getContact(name);
            if (cursor.moveToFirst()){
                rawContactId = cursor.getLong(0);
            }
            resolver.update(uri,values,"mimetype=? and raw_contact_id = ?",new String[]{
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,rawContactId + ""});
            cursor.close();
        }
    }

    public void queryAllData(View view) {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, SQL_COLUMN, null, null, null);
        printQueryResult(cursor);
    }

    private void printQueryResult(Cursor cursor) {
        if (cursor != null){
            tvContent.setText("");
            while (cursor.moveToNext()){
                String ID = cursor.getString(0);
                String contentName = cursor.getString(1);
                String phoneNumber = cursor.getString(2);
                tvContent.append("联系人ID：" + ID + "\n联系人姓名：" + contentName + "    " + phoneNumber + "\n");
            }

        }
        cursor.close();
    }
}




























