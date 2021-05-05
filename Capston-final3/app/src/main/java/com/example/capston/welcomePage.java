package com.example.capston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;


public class welcomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        startMainActivity();
    }
    private void startMainActivity() {
        TimerTask delaytask = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(welcomePage.this, MainActivity.class);
                startActivity(mainIntent);
                welcomePage.this.finish();
            }


        };
        Timer timer=new Timer();
        timer.schedule(delaytask,2000);
    }
}