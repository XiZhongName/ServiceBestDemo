package com.example.logintest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class EditProfileActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_TAKE = 1;
    public static final int REQUEST_CODE_CHOOSE = 0;
    private TextView changeImgText,tvBirthdayTime;
    private EditText etAccount,etName,etHome,etSchool,etSign;
    private Button cameraBtn,photoBtn,saveBtn;
    private Uri imageUri;
    private ImageView ivImage;
    private String imageBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        changeImgText = findViewById(R.id.change_img_text);
        tvBirthdayTime = findViewById(R.id.tv_birth_time);
        etAccount = findViewById(R.id.et_account);
        etName = findViewById(R.id.et_name);
        ivImage = findViewById(R.id.iv_image);
        //etHome = findViewById(R.id.sp_city);
        etSchool = findViewById(R.id.et_school);
        etSign = findViewById(R.id.et_sign);
        cameraBtn = findViewById(R.id.camera_btn);
        photoBtn = findViewById(R.id.photo_btn);
        saveBtn = findViewById(R.id.save_btn);

    }

    public void jumpToUser(View view) {
        String account = etAccount.getText().toString();
        String name = etName.getText().toString();
        String home = etHome.getText().toString();
        String school = etSchool.getText().toString();
        String sign = etSign.getText().toString();
        Intent intent = new Intent(EditProfileActivity.this,UserProfileActivity.class);
        SharedPreferences spfRecord = getSharedPreferences("spfRecord",MODE_PRIVATE);
        SharedPreferences.Editor edit = spfRecord.edit();
        edit.putString("account",account);
        edit.putString("name",name);
        edit.putString("home",home);
        edit.putString("school",school);
        edit.putString("sign",sign);

        Bundle bundle = new Bundle();
        bundle.putString("account",account);
        bundle.putString("name",name);
        bundle.putString("home",home);
        bundle.putString("school",school);
        bundle.putString("sign",sign);
        intent.putExtras(bundle);
        setResult(2,intent);
        startActivity(intent);
    }

    public void takeCamera(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            //判断是否有拍照权限，有的话，就可以拍照
            doTake();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                doTake();
            } else {
                Toast.makeText(this,"你没有获得摄像头权限",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE){
            if (resultCode == RESULT_OK){
                try {
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ivImage.setImageBitmap(bitmap);
                    //String imageToBase64 = ImageUtil.imageToBase64(bitmap);
                    //imageToBase64 = imageBase64;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doTake() {
        File imageTemp = new File(getExternalCacheDir(),"imageOut.jpeg");
        if (imageTemp.exists()){
            imageTemp.delete();
        }
        try {
            imageTemp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT > 24){
            imageUri = FileProvider.getUriForFile(this,"com.example.LoginTest.fileprovider",imageTemp);
        } else {
            imageUri = Uri.fromFile(imageTemp);
        }
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent, REQUEST_CODE_TAKE);
    }

    public void choosePhoto(View view) {
    }
}