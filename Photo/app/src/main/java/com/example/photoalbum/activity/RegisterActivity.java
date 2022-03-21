package com.example.photoalbum.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.photoalbum.R;
import com.example.photoalbum.bean.User;
import com.example.photoalbum.util.MySqliteOpenHelper;


/**
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private EditText etAccount;//手机号
    private EditText etNickName;//昵称
    private EditText etPassword;//密码
    private EditText etPasswordSure;//确认密码
    private Spinner spSex;//性别
    private TextView tvLogin;//登录
    private ImageView ivPhoto;//头像
    private Button btnRegister;//注册按钮
    private String imagePath = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = new MySqliteOpenHelper(this);
        etAccount = (EditText) findViewById(R.id.et_account);//获取手机号
        etNickName = (EditText) findViewById(R.id.et_nickName);//获取昵称
        etPassword = (EditText) findViewById(R.id.et_password);//获取密码
        etPasswordSure = (EditText) findViewById(R.id.et_password_sure);//获取确认密码
        spSex = findViewById(R.id.sp_sex);
        ivPhoto = findViewById(R.id.iv_photo);
        tvLogin = (TextView) findViewById(R.id.tv_login);//登录
        btnRegister = (Button) findViewById(R.id.btn_register);//获取注册按钮
        //从相册中选择头像
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,Photo.class);
                startActivity(intent);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到登录页面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //设置注册点击按钮
        btnRegister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //关闭虚拟键盘
                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //获取请求参数
                String account = etAccount.getText().toString();
                String nickName = etNickName.getText().toString();
                String password = etPassword.getText().toString();
                String passwordSure = etPasswordSure.getText().toString();
//                if ("".equals(imagePath)) {//请上传头像
//                    Toast.makeText(RegisterActivity.this, "请上传头像", Toast.LENGTH_LONG).show();
//                    return;
//                }
                if ("".equals(account)) {//账号不能为空
                    Toast.makeText(RegisterActivity.this, "账号不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(nickName)) {//昵称不能为空
                    Toast.makeText(RegisterActivity.this, "昵称不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(password)) {//密码为空
                    Toast.makeText(RegisterActivity.this, "密码为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(passwordSure)) {//密码不一致
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                    return;
                }
                String sex = spSex.getSelectedItem().toString();
                User mUser = null;
                String sql = "select * from user where account = ? ";
                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor cursor = db.rawQuery(sql, new String[]{account});
                if (cursor != null && cursor.getColumnCount() > 0) {
                    while (cursor.moveToNext()) {
                        String dbId = cursor.getString(0);
                        String dbAccount = cursor.getString(1);
                        String dbName = cursor.getString(2);
                        String dbPassword = cursor.getString(3);
                        String dbPhoto = cursor.getString(4);
                        String dbSex = cursor.getString(5);
                        mUser = new User(dbId, dbAccount, dbName, dbPassword, dbPhoto, dbSex);
                    }
                }
                if (mUser == null) {
                    String insertSql = "insert into user(account,name,password,photo,sex) values(?,?,?,?,?)";
                    db.execSQL(insertSql, new Object[]{account, nickName, password, imagePath, sex});
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "该账号已经注册", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
    }


    /**
     * 选择图片
     */
//    private void selectClick() {
//        PictureSelector.create(this)
//                .openGallery(PictureMimeType.ofAll())
//                .imageEngine(GlideEngine.createGlideEngine())
//                .maxSelectNum(1)
//                .forResult(new OnResultCallbackListener<LocalMedia>() {
//                    @Override
//                    public void onResult(List<LocalMedia> result) {
//                        for (int i = 0; i < result.size(); i++) {
//                            // onResult Callback
//                            LocalMedia media = result.get(i);
//                            String path;
//                            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
//                            boolean compressPath = media.isCompressed() || (media.isCut() && media.isCompressed());
//                            // 裁剪过
//                            boolean isCutPath = media.isCut() && !media.isCompressed();
//
//                            if (isCutPath) {
//                                path = media.getCutPath();
//                            } else if (compressPath) {
//                                path = media.getCompressPath();
//                            } else if (!TextUtils.isEmpty(media.getAndroidQToPath())) {
//                                // AndroidQ特有path
//                                path = media.getAndroidQToPath();
//                            } else if (!TextUtils.isEmpty(media.getRealPath())) {
//                                // 原图
//                                path = media.getRealPath();
//                            } else {
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                                    path = PictureFileUtils.getPath(RegisterActivity.this, Uri.parse(media.getPath()));
//                                } else {
//                                    path = media.getPath();
//                                }
//                            }
//                            imagePath = path;
//                        }
//                        Glide.with(RegisterActivity.this).load(imagePath).into(ivPhoto);
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // onCancel Callback
//                    }
//                });
//    }

    public void back(View view) {
        finish();
    }
}
