package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DBUtil.Dao;
import com.example.myapplication.DBUtil.User;

import java.util.ArrayList;

public class FpwdActivity extends AppCompatActivity {
    private EditText met1;
    private EditText met2;
    private EditText met3;
    private Button mbtn1;
    private Dao mhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpwd);
        met1 = findViewById(R.id.etfpwd_1);
        met2 = findViewById(R.id.etfpwd_2);
        met3 = findViewById(R.id.etfpwd_3);
        mbtn1 = findViewById(R.id.btfpwd_1);
        mhelper = new Dao(this);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = met1.getText().toString().trim();
                String password = met2.getText().toString().trim();
                String pwd = met3.getText().toString().trim();
                //重置密码
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(pwd) && TextUtils.equals(password,pwd)) {
                    ArrayList<User> list = mhelper.getAllData();
                    boolean flag = false;
                    for (int i = 0; i < list.size(); i++) {
                        User user = list.get(i);
                        if (name.equals(user.getUsername())) {
                            flag = true;
                            break;
                        } else {
                            flag = false;
                        }
                    }
                    if (!flag) {
                        Toast.makeText(FpwdActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mhelper.update(name, password);
                        Intent intent = new Intent(FpwdActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                        Toast.makeText(FpwdActivity.this, "密码已重置，请登录", Toast.LENGTH_SHORT).show();
                    }
                }else if (TextUtils.isEmpty(name)){
                    Toast.makeText(FpwdActivity.this, "用户名不能为空！！", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(FpwdActivity.this, "密码不能为空！！", Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.equals(password,pwd)){
                    Toast.makeText(FpwdActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
