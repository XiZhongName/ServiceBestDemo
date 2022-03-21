package com.example.greendaotest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"school.db");
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
    }
    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
