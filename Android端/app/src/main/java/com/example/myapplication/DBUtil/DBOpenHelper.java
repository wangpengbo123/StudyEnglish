package com.example.myapplication.DBUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databases.db";
    public static final int VERSION_CODE = 1;
    private SQLiteDatabase db;

    /**
     * @param context   上下文
     * @param factory   游标工厂
     * @param name      数据库名
     * @param version   版本
     */


    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, null, VERSION_CODE);
         db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建回调函数
        Log.d(TAG,"Create Database");
        //创建一个SQL语句：create table table_name(username varchar,password varchar)
        String sql = "CREATE TABLE IF NOT EXISTS User(username varchar,password varchar)";
        db.execSQL(sql);
//        String sql1 = "CREATE TABLE IF NOT EXISTS Grammar(name varchar,content varchar)";
//        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库回调函数
        Log.d(TAG,"Update Database");
    }

}
