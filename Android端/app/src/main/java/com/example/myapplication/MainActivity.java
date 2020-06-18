package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.myapplication.DBUtil.DBOpenHelper;

import static com.example.myapplication.DBUtil.DBOpenHelper.DATABASE_NAME;
import static com.example.myapplication.DBUtil.DBOpenHelper.VERSION_CODE;

public class MainActivity extends AppCompatActivity {
    private Button mbutLogin;
    private Button mbutLogon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBOpenHelper helper = new DBOpenHelper(this,DATABASE_NAME,null, VERSION_CODE);
        helper.getWritableDatabase();
        mbutLogin = findViewById(R.id.button2);
        mbutLogin.setOnClickListener(new View.OnClickListener() {//匿名内部类
            @Override
            public void onClick(View v) {
                //跳转到LoginActivity界面
                Intent Login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(Login);
            }
        });
        mbutLogon = findViewById(R.id.button);
        mbutLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到LogonActivity界面
                Intent Logon = new Intent(MainActivity.this,LogonActivity.class);
                startActivity(Logon);
            }
        });


    }
}
