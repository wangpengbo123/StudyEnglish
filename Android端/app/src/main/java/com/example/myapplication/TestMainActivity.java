package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.Connected.HttpUtil;
import com.example.myapplication.Test.Dao;
import com.example.myapplication.Test.Test;
import com.example.myapplication.Test.TestsActivity;

public class TestMainActivity extends AppCompatActivity {
    private RadioGroup mrggroup;
    private RadioButton mrbbutton1;
    private RadioButton mrbbutton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_paper);
        Dao dao = new Dao(this);
        mrbbutton1 = findViewById(R.id.start);
        mrbbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent four = new Intent(TestMainActivity.this,TestsActivity.class);
                startActivity(four);
                finish();
            }
        });
        mrbbutton2 = findViewById(R.id.flush);
        mrbbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        String json1 = HttpUtil.queryStringForPost("http://192.168.0.106:8080/login/flushQuestionServlet");
                        JSONObject obj1 = JSONObject.parseObject(json1);
                        JSONArray questions = obj1.getJSONArray("query");
                        JSONArray deletedTest = obj1.getJSONArray("deletedTest");
                        for (Object o:questions) {
                            JSONObject jsonObjecto =(JSONObject)o;
                            int id = jsonObjecto.getInteger("id");
                            String question = jsonObjecto.getString("question");
                            String answer = jsonObjecto.getString("answer");
                            String optionA = jsonObjecto.getString("optionA");
                            String optionB = jsonObjecto.getString("optionB");
                            String optionC = jsonObjecto.getString("optionC");
                            String optionD = jsonObjecto.getString("optionD");

                            /*String question = test.getQuestion();
                            String answer = test.getAnswer();
                            String optionA = test.getOption_A();
                            String optionB = test.getOption_B();
                            String optionC = test.getOption_C();
                            String optionD = test.getOption_D();*/
                            dao.insert(id,question,answer,optionA,optionB,optionC,optionD);


                        }
                        if (deletedTest != null && deletedTest.size()!=0) {
                            for (Object o : deletedTest) {
                                int id = (Integer)o;
                                dao.delete(id);
                            }
                        }
                    }
                }.start();
            }
        });
        mrggroup = findViewById(R.id.rg_1);
        mrggroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                Toast.makeText(TestMainActivity.this, radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
