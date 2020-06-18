package com.example.myapplication.Search;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DBUtil.DBOpenHelper;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class WordsActivity extends AppCompatActivity {
    private DBOpenHelper mhepler;
    private SQLiteDatabase db;
    private AutoCompleteTextView mactv;
    private Button mSearchbtn,mupdatebtn;
    private TextView showResult;
    private String word, meaning;
    private InputStreamReader isr;
    private BufferedReader br;
//    private ArrayList<Word> list;
//    private Word word1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        mactv = findViewById(R.id.actv_1);
        mSearchbtn = findViewById(R.id.wdib_1);
        mupdatebtn = findViewById(R.id.wdib_2);
        showResult = findViewById(R.id.wdtv_1);
        mhepler = new DBOpenHelper(this, DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.VERSION_CODE);
        db = mhepler.getReadableDatabase();
        /*list = new ArrayList<>();
        Cursor cursor = db.query("Words", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            word = cursor.getString(0);
            meaning = cursor.getString(1);
            word1 = new Word(word, meaning);
            list.add(word1);
        }*/
        mSearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String words = mactv.getText().toString().trim();
                ArrayList<Word> list = getAllData();
                System.out.println(list.size());
                Word word1 = null;
                if (!TextUtils.isEmpty(words)) {
                  /*  for (Word word1 : list) {
                    }*/
                    for (int i = 0; i < list.size()-1; i++) {
                        word1 = list.get(i);

                        if (word1.getWord().equals(words)) {
                            String result = word1.getMeaning();
                            Log.d(words,result);
                            showResult.setText(words+"\n"+ result);
                            break;
                        }
                    }
                    if (!word1.getWord().equals(words)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(WordsActivity.this);
                        builder.setTitle("提示")
                                .setMessage("没有查询到该单词")
                                .setPositiveButton("确定", null)
                                .show();
                    }
                } else {
                    Toast.makeText(WordsActivity.this, "请输入要查询的单词", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    isr = new InputStreamReader(getResources().openRawResource(R.raw.words));
                    br = new BufferedReader(isr);
                    String line = null;
                    while((line = br.readLine())!=null){
                        String[] s = line.split(" ");
                        String word = s[0];
                        String meaning = s[1];
                        String sql = "insert into Words (word,meaning) values(?,?)";
                        db.execSQL(sql, new Object[]{word, meaning});
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (br != null){
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (isr != null){
                        try {
                            isr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Toast.makeText(WordsActivity.this, "词库更新成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Word> getAllData(){

        ArrayList<Word> list = new ArrayList<>();
        Cursor cursor = db.query("Words",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String english = cursor.getString(cursor.getColumnIndex("word"));
            String chinese = cursor.getString(cursor.getColumnIndex("meaning"));
            list.add(new Word(english,chinese));
        }
        return list;
    }
}


   /*
    private ArrayList<HashMap<String,String>> getData() {
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        Cursor cursor = db.query("Words", null, null, null, null, null, "word DESC");
        while(cursor.moveToNext()){
            word= cursor.getString(0);
            meaning = cursor.getString(1);
            map.put(word,meaning);
        }
        list.add(map);
        return list;
    }*/
