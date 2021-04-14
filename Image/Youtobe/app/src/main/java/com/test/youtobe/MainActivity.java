package com.test.youtobe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MainActivity extends AppCompatActivity {
    YouTubePlayerView youtobe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youtobe = findViewById(R.id.youtobeVIew);
        getLifecycle().addObserver(youtobe);
    }
}