package com.example.myapplication.Grammar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.myapplication.DBUtil.DBOpenHelper;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
//import com.example.myapplication.Grammar.Dao;


import com.example.myapplication.DBUtil.DBOpenHelper;
import com.example.myapplication.InnerfourActivity;
import com.example.myapplication.Main1Activity;
import com.example.myapplication.R;

import java.util.ArrayList;


public class GrammarActivity extends AppCompatActivity {
    private Button mbtn1;
    private Button mbtn2;
    private Button mbtn3;
    private Button mbtn4;
    private Button mbtn5;
    private Button mbtn6;
    private SQLiteDatabase db;
    private DBOpenHelper mhelper;
    private static final String TABLE_NAME = "Grammer";

    /*public GrammarActivity(){}
    public GrammarActivity(SQLiteDatabase db, SQLiteOpenHelper mhelper) {
        this.db = db;
        this.mhelper = mhelper;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        mhelper = new DBOpenHelper(this, DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.VERSION_CODE);
        db = mhelper.getReadableDatabase();
        mbtn1 = findViewById(R.id.btngra_1);
        mbtn2 = findViewById(R.id.btngra_2);
        mbtn3 = findViewById(R.id.btngra_3);
        mbtn4 = findViewById(R.id.btngra_4);
        mbtn5 = findViewById(R.id.btngra_5);
        mbtn6 = findViewById(R.id.btngra_6);


        setListeners();
        /*mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,GraPartsActivity.class);

            }
        });*/
    }




    public void setListeners(){
        OnClick onClick = new OnClick();
        mbtn1.setOnClickListener(onClick);
        mbtn2.setOnClickListener(onClick);
        mbtn3.setOnClickListener(onClick);
        mbtn4.setOnClickListener(onClick);
        mbtn5.setOnClickListener(onClick);
        mbtn6.setOnClickListener(onClick);

    }
    public class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
//            db = mhelper.getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,"name DESC");
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String content = cursor.getString(1);
            switch (v.getId()){
                case R.id.btngra_1:
                    if ("词类".equals(name)) {
                            Intent intent = new Intent(GrammarActivity.this, GraPartsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", name);
                            bundle.putString("content", content);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                    }
                    break;
                case R.id.btngra_2:
                    if ("时态".equals(name)){
                        Intent intent = new Intent(GrammarActivity.this,GraPartsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("content",content);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                    break;
                case R.id.btngra_3:
                    if ("语态".equals(name)){
                        Intent intent = new Intent(GrammarActivity.this,GraPartsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("content",content);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                    break;
                case R.id.btngra_4:
                    if ("语气".equals(name)){
                        Intent intent = new Intent(GrammarActivity.this,GraPartsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("content",content);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                    break;
                case R.id.btngra_5:
                    if ("句子成分".equals(name)){
                        Intent intent = new Intent(GrammarActivity.this,GraPartsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("content",content);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                case R.id.btngra_6:
                    if ("句型结构".equals(name)){
                        Intent intent = new Intent(GrammarActivity.this,GraPartsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putString("content",content);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                    break;
                }
            }
        }
    }
}
