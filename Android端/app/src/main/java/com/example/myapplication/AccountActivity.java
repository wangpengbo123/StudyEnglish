package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.DBUtil.Dao;

public class AccountActivity extends AppCompatActivity {
    private ImageView miv1;
    private TextView mtv1,mtv2;
    private Button mbtn1;
    private Button mbtn2,mbtn3;
    private Dao mhelper;
    private Handler mhandler;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        miv1 = findViewById(R.id.ivact_1);
        mtv1 = findViewById(R.id.tvact_3);
        mtv2 = findViewById(R.id.edact_1);
        mbtn1 = findViewById(R.id.btnact_1);
        mbtn2 = findViewById(R.id.btnact_2);
        mbtn3 = findViewById(R.id.btnact_3);
        mhelper = new Dao(this);
//        Glide.with(this).load("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=2&pn=0&spn=0&di=7480&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=1035415831%2C1465727770&os=2036467054%2C2328224179&simid=4030878874%2C470441821&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fa3.att.hudong.com%2F68%2F61%2F300000839764127060614318218_950.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3F4_z%26e3B6zan0c_z%26e3Bv54AzdH3FkkfAzdH3Fp5rtv_z%26e3Bwfrx%3Ft1%3Dd8ln08c&gsm=1&islist=&querylist=").into(miv1);
        Intent intent = getIntent();
        bundle = intent.getExtras();
        assert bundle != null;
        final String name = bundle.getString("username");
//        final String password = bundle.getString("password");
        mtv1.setText(name);
        /*Intent intent1 = new Intent();
        Bundle bundle1 = intent1.getExtras();
        assert bundle1 != null;
        String content = "个性签名";
        content= bundle1.getString("content");
        mtv2.setText(content);
        */
        String trim = mtv2.getText().toString().trim();
        mtv2.setText(trim);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(AccountActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
            }
        });

        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhelper.delete(name);
                mhandler = new Handler();
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AccountActivity.this,LogonActivity.class);
                        startActivity(intent);
                        Toast.makeText(AccountActivity.this, "账户已注销！", Toast.LENGTH_SHORT).show();
                    }
                },3000);
                finish();
            }
        });
        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this,UpdateActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
