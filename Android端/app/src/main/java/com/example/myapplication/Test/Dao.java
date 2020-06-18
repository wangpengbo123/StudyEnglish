package com.example.myapplication.Test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DBUtil.DBOpenHelper;

import java.util.ArrayList;

import static com.example.myapplication.DBUtil.DBOpenHelper.DATABASE_NAME;
import static com.example.myapplication.DBUtil.DBOpenHelper.VERSION_CODE;

public class Dao {
    private DBOpenHelper mhelper;
    private SQLiteDatabase db;

    public Dao(Context context) {
        mhelper = new DBOpenHelper(context, DATABASE_NAME, null, VERSION_CODE);
        db = mhelper.getReadableDatabase();
    }
    public Long insert(String question, String answer, String optionA,String optionB,String optionC,String optionD) {
        ContentValues cv = new ContentValues();
        cv.put("question", question);
        cv.put("answer", answer);
        cv.put("optionA", optionA);
        cv.put("optionB", optionB);
        cv.put("optionC", optionC);
        cv.put("optionD", optionD);
        return db.insert("Questions", null, cv);
//        db.execSQL("INSERT INTO Message (name,content,remark) VALUES(?,?,?)",new Object[]{name,message,remark});
    }
    public ArrayList<Test> getAllData() {

        ArrayList<Test> list = new ArrayList<>();
        Cursor cursor = db.query("Questions", null, null, null, null, null, "id DESC");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String question = cursor.getString(cursor.getColumnIndex("question"));
            String answer = cursor.getString(cursor.getColumnIndex("answer"));
            String optionA = cursor.getString(cursor.getColumnIndex("optionA"));
            String optionB = cursor.getString(cursor.getColumnIndex("optionB"));
            String optionC = cursor.getString(cursor.getColumnIndex("optionC"));
            String optionD = cursor.getString(cursor.getColumnIndex("optionD"));
            list.add(new Test(id,question,answer,optionA,optionB,optionC,optionD));
        }
        return list;
    }
    public void delete(int id){
        //db.execSQL("DELETE FROM User WHERE username="+username);no such column: xiaoming (code 1): , while compiling: DELETE FROM User WHERE username =xiaoming
        String sql = "delete from Questions where id = ?";
        db.execSQL(sql,new Object[]{id});
    }

    public void insert(int id, String question, String answer, String optionA, String optionB, String optionC, String optionD) {
        String sql = "insert into Questions (id,question,answer,optionA,optionB,optionC,optionD) values(?,?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{id,question,answer,optionA,optionB,optionC,optionD});
    }
}
