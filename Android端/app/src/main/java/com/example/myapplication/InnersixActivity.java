package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.Grammar.GraMainActivity;
import com.example.myapplication.Grammar.GrammarActivity;
import com.example.myapplication.Message.AdapterActivity;
import com.example.myapplication.Message.LeaveMessageActivity;
import com.example.myapplication.Search.WordsActivity;
import com.example.myapplication.Test.Dao;
import com.example.myapplication.Test.Test;
import com.example.myapplication.Test.TestsActivity;

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
//import com.example.myapplication.Message.ListActivity;

public class InnersixActivity extends AppCompatActivity {
    private Button mbtn1;
    private Button mbtn2;
    private Button mbtn3;
    private Button mbtn4;
    private final static String SUBMIT_URL = "http://192.168.0.106:8080/login/submit_Questions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innersix);
        Dao dao = new Dao(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        final String name = bundle.getString("username");
        final String password = bundle.getString("password");
        mbtn1 = findViewById(R.id.btnsix_1);
        mbtn2 = findViewById(R.id.btnsix_2);
        mbtn3 = findViewById(R.id.btnsix_3);
        mbtn4 = findViewById(R.id.btnsix_4);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnersixActivity.this, GraMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InnersixActivity.this, TestMainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InnersixActivity.this, WordsActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        mbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnersixActivity.this, AdapterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",name);
                bundle.putString("password",password);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }
    public void submitToServer(final Long id, final String question, final String answer, final String optionA,final String optionB,final String optionC,final String optionD) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {


                HttpParams httpParameters = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParameters, 20 * 1000);
                HttpConnectionParams.setSoTimeout(httpParameters, 10 * 1000);
                HttpClient httpClient = new DefaultHttpClient(httpParameters);
//                // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//                CloseableHttpClient httpClient = HttpClients.createDefault();
                // 创建Post请求
                HttpPost httpPost = new HttpPost(SUBMIT_URL);
                //拼接多参数
                JSONObject json = new JSONObject();
                json.put("id", id);
                json.put("question", question);
                json.put("answer", answer);
                json.put("optionA", optionA);
                json.put("optionB", optionB);
                json.put("optionC", optionC);
                json.put("optionD", optionD);

                try {
                    httpPost.addHeader("Content-type", "application/json; charset=utf-8");
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setEntity(new StringEntity(json.toString(), "utf-8"));
                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity entity = httpResponse.getEntity();
                    System.err.println("状态:" + httpResponse.getStatusLine());
                    System.err.println("参数:" + EntityUtils.toString(entity));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
}
