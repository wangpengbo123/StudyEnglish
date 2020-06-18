package com.example.myapplication.Test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.accessibility.AccessibilityViewCommand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.Connected.HttpUtil;
import com.example.myapplication.DBUtil.DBOpenHelper;
import com.example.myapplication.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class TestsActivity extends AppCompatActivity {
    private TextView mtv1, mtv2, mtv3, mtv4, mtv5, mtv6, mtv7, mtv8;
    private RadioGroup mrg;
    private Button mbtn1, mbtn2, mbtn3;
    private DBOpenHelper mhelper;
    private SQLiteDatabase db;
    private Test test;
    private int index;
    private int count = 0;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mhelper = new DBOpenHelper(this, DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.VERSION_CODE);
        db = mhelper.getReadableDatabase();
        mbtn1 = findViewById(R.id.tsbtn_1);
        mbtn2 = findViewById(R.id.tsbtn_2);
        mbtn3 = findViewById(R.id.tsbtn_3);
        mrg = findViewById(R.id.tsrg_1);
        mtv1 = findViewById(R.id.tstv_1);
        mtv2 = findViewById(R.id.tstv_2);
        mtv3 = findViewById(R.id.tstv_3);
        mtv4 = findViewById(R.id.tstv_4);
        mtv5 = findViewById(R.id.tstv_5);
        mtv6 = findViewById(R.id.tscount_2);
        mtv7 = findViewById(R.id.tscount_3);
        mtv8 = findViewById(R.id.tscount_5);
        final ArrayList<Test> list = getAllData();
//        for (int j = 0; j < list.size();j++) {
        test = list.get(0);
        mtv1.setText(test.getQuestion());
        mtv2.setText(test.getOption_A());
        mtv3.setText(test.getOption_B());
        mtv4.setText(test.getOption_C());
        mtv5.setText(test.getOption_D());
        mtv6.setText(list.size() + "");

        mtv8.setText(list.size() + "");
        chronometer = findViewById(R.id.tschronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setFormat("%s");
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime() - chronometer.getBase() >= 60000) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TestsActivity.this);

                    builder.setMessage("您已用时60s，不合格" + "\n" + "Come on!");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                    chronometer.stop();
                }
            }
        });

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    test = list.get(j);
                if (index < list.size() - 1) {
                    mrg.clearCheck();
                    index = index + 1;
                    test = list.get(index);
                    mtv1.setText(test.getQuestion());
                    mtv2.setText(test.getOption_A());
                    mtv3.setText(test.getOption_B());
                    mtv4.setText(test.getOption_C());
                    mtv5.setText(test.getOption_D());
                    mtv7.setText((index+1)+ "");

                } else {
                    Toast.makeText(TestsActivity.this, "最后一题了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j = 0; j < index + 1; j++) {
                    for (int i = 0; i < mrg.getChildCount(); i++) {
                        RadioButton mrbtn = (RadioButton) mrg.getChildAt(i);
                        if (mrbtn.isChecked()) {
                            if (mrbtn.getText().equals(test.getAnswer())) {
                                count++;
                            }

                        }
                    }
                }


                AlertDialog.Builder builder = new AlertDialog.Builder(TestsActivity.this);

                builder.setMessage("本次总共答题：" + (index + 1) + "\n" + "本次答题所用时间为：" + (SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 + "秒" + "\n"
                        + "本次做对题目为：" + count + "\n" + "错题为：" + ((index + 1) - count) + "\n" + "Great! Go on!");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
                chronometer.stop();

            }
        });

        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    Toast.makeText(TestsActivity.this, "已经是第一题了", Toast.LENGTH_SHORT).show();
                } else {
                    index = index - 1;
                    test = list.get(index);
                    mtv1.setText(test.getQuestion());
                    mtv2.setText(test.getOption_A());
                    mtv3.setText(test.getOption_B());
                    mtv4.setText(test.getOption_C());
                    mtv5.setText(test.getOption_D());

                }
            }
        });
//        Chronometer chronometer = findViewById(R.id.tschronometer);
//        chronometer.setBase(SystemClock.elapsedRealtime());
//        chronometer.setFormat("%s");
//        chronometer.start();

    }

    public ArrayList<Test> getAllData() {

        ArrayList<Test> list = new ArrayList<>();
        Cursor cursor = db.query("Questions", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String question = cursor.getString(cursor.getColumnIndex("question"));
            String answer = cursor.getString(cursor.getColumnIndex("answer"));
            String option_A = cursor.getString(cursor.getColumnIndex("optionA"));
            String option_B = cursor.getString(cursor.getColumnIndex("optionB"));
            String option_C = cursor.getString(cursor.getColumnIndex("optionC"));
            String option_D = cursor.getString(cursor.getColumnIndex("optionD"));
            Test test = new Test(id, question, answer, option_A, option_B, option_C, option_D);
            list.add(test);
        }
        return list;
    }

}
