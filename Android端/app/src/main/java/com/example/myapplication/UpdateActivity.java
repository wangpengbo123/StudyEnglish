package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {
    private TextView mtv;
    private Button mbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mtv = findViewById(R.id.uptv_2);
        mbtn = findViewById(R.id.upbtn_1);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = mtv.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)){
                    Bundle bundle = new Bundle();
                    bundle.putString("content",trim);
                    Intent intent1 = new Intent(UpdateActivity.this, AccountActivity.class);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                    finish();
                }
            }
        });
    }
}
