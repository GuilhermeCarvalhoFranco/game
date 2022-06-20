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

    boolean isPlayingTheme = false, isPlayingMenu = false, isPlayingSword = false;
    boolean wasStarted = false;
    boolean isMenu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();

        layoutLandscape();

        menuSoundtrack();
    }

    public void layoutLandscape(){
        int requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        setRequestedOrientation(requestedOrientation);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void menuSoundtrack(){
        MediaPlayer mpMenu;
        mpMenu = MediaPlayer.create(this, R.raw.menu);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isMenu){
                    isPlayingMenu = mpMenu.isPlaying();

                    if (!isPlayingMenu){
                        mpMenu.stop();
                        mpMenu.start();
                    }
                }
            }
        }).start();
    }
    public void startGame(View v){
        isMenu = false;
        wasStarted = true;

        startSoundtrack();
    }

    public void startSoundtrack(){
        MediaPlayer mpSword;
        mpSword = MediaPlayer.create(this, R.raw.espada);

        mpSword.start();
        int tempo = mpSword.getDuration();

        new Thread(new Runnable() {
            @Override
            public void run() {
                themeSoundtrack();
                try {
                    Thread.sleep((tempo * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void themeSoundtrack(){
        MediaPlayer mpTheme;
        mpTheme = MediaPlayer.create(this, R.raw.theme);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(wasStarted){
                    isPlayingTheme = mpTheme.isPlaying();

                    if (!isPlayingTheme){
                        mpTheme.start();
                    }
                }
            }
        }).start();
    }

}