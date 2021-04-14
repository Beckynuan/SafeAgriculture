package com.example.vedioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1=findViewById(R.id.videoView);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(v1);

        Uri uri=Uri.parse("https://qiniu-xpc0.xpccdn.com/5b4c8f1691dea.mp4");
        v1.setMediaController(mediaController);
        v1.setVideoURI(uri);
        v1.start();



    }
}