package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DBUtil.Dao;
import com.example.myapplication.DBUtil.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button mbutlogin;
    private TextView mtv1;
    private EditText met1;
    private EditText met2;
    private Dao mhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mhelper = new Dao(this);
        mbutlogin = findViewById(R.id.btnlogin);
        mtv1 = findViewById(R.id.tvlogin_1);
        met1 = findViewById(R.id.etlogin_1);
        met2 = findViewById(R.id.etlogin_2);

        mtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent issue = new Intent(LoginActivity.this,FpwdActivity.class);
                startActivity(issue);
            }
        });
        mbutlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = met1.getText().toString().trim();
                String password = met2.getText().toString().trim();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mhelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Main1Activity.class);
//                        startActivity(intent);
//                        Intent intent1 = new Intent(LoginActivity.this, AccountActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",username);
                        bundle.putString("password",password);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else if (TextUtils.isEmpty(username)){
                    Toast.makeText(LoginActivity.this, "用户名不能为空！！", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "密码不能为空！！", Toast.LENGTH_SHORT).show();
                }

            }
                
        });

    }


    public void showToast(View view){
        Toast.makeText(this, "登陆成功！", Toast.LENGTH_LONG).show();
    }
}
