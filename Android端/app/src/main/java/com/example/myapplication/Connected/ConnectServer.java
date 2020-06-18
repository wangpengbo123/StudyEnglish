package com.example.myapplication.Connected;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.myapplication.Message.AdapterActivity;
import com.example.myapplication.Message.Dao;
import com.example.myapplication.Message.LeaveMessage;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Map;


public class ConnectServer extends AppCompatActivity {
    private TextView mtv;
    LeaveMessage lm = null;
    String json = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_server);
//        DefaultHttpClient httpClient = new DefaultHttpClient();
        mtv = findViewById(R.id.tvadp_1);
        Dao dao = new Dao(this);
//        ReadUrl("http://baidu.com");
        new Thread() {
            @Override
            public void run() {
                json = HttpUtil.queryStringForPost("http://192.168.0.106:8080/login/connectAppServlet");
                try {
                    JSONObject obj = JSONObject.parseObject(json);
                    JSONArray deleteMsgs = obj.getJSONArray("deleteMsg");
                    JSONArray remarksMsgs = obj.getJSONArray("remarkMsg");
                    ArrayList<LeaveMessage> allData = dao.getAllData();
                    if (deleteMsgs != null && deleteMsgs.size() != 0) {
                        for (Object id : deleteMsgs) {
                            int idI = (Integer) id;
//                    dao.dele()
                            for (LeaveMessage leaveMessage:allData) {
                                if (idI==leaveMessage.getId()){
                                    dao.delete(idI);
                                }else{
                                    System.out.println("第"+idI+"此条记录已删除");
                                }
                            }
                        }
                    }

                    if (remarksMsgs != null && remarksMsgs.size() != 0) {
                        for (Object o : remarksMsgs) {
                            Map<String, Object> objj = (Map<String, Object>) o;
                            String id = objj.get("id").toString();
                            String remark = objj.get("remark").toString();
                            System.out.println(id);
//                    dao.update()
                            dao.update(Integer.valueOf(id), remark);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Intent intent1 = new Intent(ConnectServer.this, AdapterActivity.class);
        Toast.makeText(ConnectServer.this, "留言数据已更新成功！", Toast.LENGTH_SHORT).show();
        startActivity(intent1);
        finish();
    }

}
