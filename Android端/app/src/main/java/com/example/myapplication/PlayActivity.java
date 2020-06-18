package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {
    private VideoView mvv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mvv1 = findViewById(R.id.videoView);
        String videoUrl1 = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";   // 或者 file:///storage/emulated/0/test.mp4
        Uri uri = Uri.parse( videoUrl1 );
        mvv1.setMediaController(new MediaController(this));
        mvv1.setVideoURI(uri);
        mvv1.start();

    }
}
