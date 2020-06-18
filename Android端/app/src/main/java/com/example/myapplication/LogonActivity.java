package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.DBUtil.Dao;
import com.example.myapplication.DBUtil.User;
import java.util.ArrayList;

public class LogonActivity extends AppCompatActivity {
    private Button mbtnregister;
    private EditText mrtusername;
    private EditText metpassword;
    private EditText metpsd;
    private TextView mtv1;
    private Dao mhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        mhelper = new Dao(this);
        mrtusername = findViewById(R.id.username);
        metpassword = findViewById(R.id.password);
        metpsd = findViewById(R.id.password1);
        mbtnregister = findViewById(R.id.button3);
        mtv1 = findViewById(R.id.tvlohon_2);
        mbtnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mrtusername.getText().toString().trim();
                String password = metpassword.getText().toString().trim();
                String psd = metpsd.getText().toString().trim();
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(psd) && TextUtils.equals(password,psd)) {
                    ArrayList<User> data = mhelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (username.equals(user.getUsername())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(LogonActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mhelper.insert(username, password);
                        Intent intent = new Intent(LogonActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                        Toast.makeText(LogonActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                    }
                }else if (TextUtils.isEmpty(username)){
                    Toast.makeText(LogonActivity.this, "用户名不能为空！！", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password) || TextUtils.isEmpty(psd)){
                    Toast.makeText(LogonActivity.this, "密码不能为空！！", Toast.LENGTH_SHORT).show();
                }else if(!TextUtils.equals(password,psd)){
                    Toast.makeText(LogonActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }

        });

        mtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(LogonActivity.this,LoginActivity.class);
                startActivity(toLogin);
            }
        });


    }
}
