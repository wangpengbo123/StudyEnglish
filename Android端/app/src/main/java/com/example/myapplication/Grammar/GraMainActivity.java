package com.example.myapplication.Grammar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;

import com.example.myapplication.PlayActivity;
import com.example.myapplication.R;

public class GraMainActivity extends AppCompatActivity {
    private RadioButton mrb1;
    private RadioButton mrb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra_main);
        mrb1 = findViewById(R.id.grastart);
        mrb2 = findViewById(R.id.graflush);
        mrb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraMainActivity.this,GrammarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mrb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent1 = new Intent(Intent.ACTION_VIEW);
                String type = "video/*";
                Uri uri = Uri.parse("https://haokan.baidu.com/v?vid=11597538524496139917&pd=bjh&fr=bjhauthor&type=video");
                intent1.setDataAndType(uri,type);
                startActivity(intent1);*/
                Intent intent1 = new Intent(GraMainActivity.this, PlayActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}
