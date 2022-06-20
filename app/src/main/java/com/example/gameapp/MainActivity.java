package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private Handler handler;

    MediaPlayer mp;

    boolean isPlaying = false;
    boolean wasStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();

        layoutLandscape();
    }

    public void layoutLandscape(){
        int requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        setRequestedOrientation(requestedOrientation);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void startGame(View v){
        wasStarted = true;

        themeSoundtrack();
    }

    public void themeSoundtrack(){
        mp = MediaPlayer.create(this, R.raw.theme);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(wasStarted){
                    isPlaying = mp.isPlaying();

                    if (!isPlaying){
                        mp.start();
                    }
                }
            }
        }).start();
    }

}