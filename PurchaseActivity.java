package com.example.capston;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class PurchaseActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private Button playBtn, stopBtn, continueBtn;
    MediaController mMediaController;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        mVideoView = new VideoView(this);
        mVideoView = (VideoView) findViewById(R.id.video);
        mMediaController = new MediaController(this);
        playBtn = (Button) findViewById(R.id.playbutton);
        stopBtn = (Button) findViewById(R.id.stopbutton);
        continueBtn= (Button) findViewById(R.id.continuebutton);
        playBtn.setOnClickListener(new onClick());
        stopBtn.setOnClickListener(new onClick());
        continueBtn.setOnClickListener(new onClick()); }
    class onClick implements View.OnClickListener {
        public void onClick(View v) {
            //String uri2 = "http://ali.cdn.kaiyanapp.com/1604566046042_691316c2.mp4?auth_key=1616319405-0-0-be0fc0f36a6e5c1e36fe6c896658f4a4";
            String uri2="android.resource://"+getPackageName()+"/"+R.raw.chongqingexpress_dreamman;
            mVideoView.setVideoURI(Uri.parse(uri2));
            mMediaController.setMediaPlayer(mVideoView);
            mVideoView.setMediaController(mMediaController);
            mMediaController.setAnchorView(mVideoView);
            if(v==playBtn){
                mVideoView.start();
            }
            if (v ==continueBtn ) {
                mVideoView.resume();
                //mVideoView.seekTo();
            } else if (v == stopBtn) {
                mVideoView.stopPlayback();
            }
        }
    }
}