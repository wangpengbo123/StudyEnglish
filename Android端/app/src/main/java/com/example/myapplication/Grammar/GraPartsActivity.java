package com.example.myapplication.Grammar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;

public class GraPartsActivity extends AppCompatActivity {
    private TextView mtv1;
    private TextView mtv2;
    private ImageButton mimgbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra_parts);
        mtv1 = findViewById(R.id.tvpart_1);
        mtv2 = findViewById(R.id.tvpart_2);
        mimgbtn1 = findViewById(R.id.imgbtnpart_1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        String name = bundle.getString("name");
        String content = bundle.getString("content");
        mtv1.setText(name);
        mtv2.setText(content);
        mimgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GraPartsActivity.this,GrammarActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
