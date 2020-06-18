package com.example.myapplication.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Connected.ConnectServer;
import com.example.myapplication.Connected.HttpUtil;
import com.example.myapplication.DBUtil.DBOpenHelper;
import com.example.myapplication.R;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.LogFactoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

public class AdapterActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private DBOpenHelper mdboh;
    private String username;
    private String content;
    private ImageButton mibtn1;
    private Button mbtn;
    private TextView mtv;
    private Log logger = LogFactoryImpl.getLog(getClass());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        mbtn = findViewById(R.id.adpbtn_1);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1 = new Intent(AdapterActivity.this,ConnectServer.class);
            startActivity(intent1);
            finish();
            }
        });


        mibtn1 = findViewById(R.id.adpib_1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String name = bundle != null ? bundle.getString("username") : null;
        System.out.println(name);
        mibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdapterActivity.this, LeaveMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",name);
//                bundle.putString("password",password);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        mdboh = new DBOpenHelper(this,DBOpenHelper.DATABASE_NAME,null,DBOpenHelper.VERSION_CODE);
        db = mdboh.getReadableDatabase();
        ArrayList<LeaveMessage> list = new ArrayList<>();
        Cursor cursor = db.query("Message", null, null, null, null, null, "id DESC");
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            username = cursor.getString(1);
            content = cursor.getString(2);
            String remark = cursor.getString(3);
            LeaveMessage lm = new LeaveMessage(id,username,content,remark);
            list.add(lm);
        }


        /*ArrayList<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        map.put("name",username);
        map.put("content",content);
        list.add(map);*/
        //AdapterView
        ListView mlv = findViewById(R.id.list_view);
//        String[] str = {"a","b","c","d","e","f"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);
//        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.layout_list_item,new String[]{"name","content"},new int[]{R.id.litv_name,R.id.litv_message});
        ListAdapter adapter = new ListAdapter(list,this);
        mlv.setAdapter(adapter);
    }

}
