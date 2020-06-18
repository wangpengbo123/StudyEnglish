package com.example.myapplication.Message;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
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
import java.nio.charset.Charset;

public class LeaveMessageActivity extends AppCompatActivity {
    private static final String SUBMIT_URL = "http://192.168.0.106:8080/login/submitServlet";
    private EditText met1;
    private Button mbt1;
    private SQLiteDatabase db;
    private Dao mopenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_message);
        mopenHelper = new Dao(this);
//        db = mopenHelper.getWritableDatabase();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        final String name = bundle.getString("username");
        met1 = findViewById(R.id.lmet_1);
        mbt1 = findViewById(R.id.lmbtn_1);
        mbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = met1.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
//                    db.execSQL("INSERT INTO Message (name,content) VALUES(?,?)",new Object[]{name,content});
//                    System.out.println(name+content);
//                    ArrayList<LeaveMessage> list = mopenHelper.getAllData();
                    String remark = null;
                    Long id = mopenHelper.insert(name, content, remark);
                    submitToServer(id, name, content, remark);
                    //发送到服务端保存
                    Intent intent1 = new Intent(LeaveMessageActivity.this, AdapterActivity.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(LeaveMessageActivity.this, "留言不能为空", Toast.LENGTH_SHORT).show();
                }
                /*ContentValues contentValues = new ContentValues();
                contentValues.put("name",name);
                contentValues.put("content",content);
                db.insert("Message",null,contentValues);*/

            }
        });

    }

    /**
     * POST---有参测试(对象参数)
     *
     * @param id
     * @param name
     * @param content
     * @param remark
     * @date 2018年7月13日 下午4:18:50
     */
    public void submitToServer(final Long id, final String name, final String content, final String remark) {
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
                json.put("name", name);
                json.put("content", content);
                json.put("remark", remark);

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
