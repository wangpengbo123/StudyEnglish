package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main1Activity extends AppCompatActivity {
    private RadioGroup mrggroup;
    private RadioButton mrbbutton1;
    private RadioButton mrbbutton2;
    private RadioButton mrbbutton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        final String name = bundle.getString("username");
        final String password = bundle.getString("password");
        mrbbutton1 = findViewById(R.id.cet4);
        mrbbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent four = new Intent(Main1Activity.this,InnerfourActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",name);
                bundle.putString("password",password);
                four.putExtras(bundle);
                startActivity(four);
            }
        });
        mrbbutton2 = findViewById(R.id.cet6);
        mrbbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent six = new Intent(Main1Activity.this,InnersixActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",name);
                bundle.putString("password",password);
                six.putExtras(bundle);
                startActivity(six);
            }
        });
        mrggroup = findViewById(R.id.rg_1);
        mrggroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                Toast.makeText(Main1Activity.this, radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
        mrbbutton3 = findViewById(R.id.countent);
        mrbbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countent = new Intent(Main1Activity.this,AccountActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",name);
                bundle.putString("password",password);
                countent.putExtras(bundle);
                startActivity(countent);
            }
        });
    }
}
