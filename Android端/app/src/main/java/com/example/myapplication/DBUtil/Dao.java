package com.example.myapplication.DBUtil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.myapplication.DBUtil.DBOpenHelper.DATABASE_NAME;
import static com.example.myapplication.DBUtil.DBOpenHelper.VERSION_CODE;

public class Dao {
    private final DBOpenHelper mhelper;
    private SQLiteDatabase db;
    public static final String TABLE_NAME = "User";
    public static final String USER_NAME = "username";
    public Dao(Context context){
        mhelper = new DBOpenHelper(context,DATABASE_NAME,null,VERSION_CODE);
        db = mhelper.getReadableDatabase();
    }
    public void insert(String username,String password){
        db.execSQL("INSERT INTO User (username,password) VALUES(?,?)",new Object[]{username,password});
    }
    //使用Android API进行删除
    public void delete(String username){
        //db.execSQL("DELETE FROM User WHERE username="+username);no such column: xiaoming (code 1): , while compiling: DELETE FROM User WHERE username =xiaoming
        db.delete(TABLE_NAME,USER_NAME + "='" +username+"'",null );
    }
    public void update(String username,String password){
        db.execSQL("UPDATE User  SET password = ? WHERE username = ?",new Object[]{password,username});

    }
    /**
     * 前三个没啥说的，都是一套的看懂一个其他的都能懂了
     * 下面重点说一下查询表user全部内容的方法
     * 我们查询出来的内容，需要有个容器存放，以供使用，
     * 所以定义了一个ArrayList类的list
     * 有了容器，接下来就该从表中查询数据了，
     * 这里使用游标Cursor，这就是数据库的功底了，
     * 在Android中我就不细说了，因为我数据库功底也不是很厚，
     * 但我知道，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，
     *                                                     最后是查询出来内容的排序方式："name DESC"
     * 游标定义好了，接下来写一个while循环，让游标从表头游到表尾
     * 在游的过程中把游出来的数据存放到list容器中
     * @return
     */
    public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = db.query("User",null,null,null,null,null,"username DESC");
        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(username,password));
        }
        return list;
    }

}
