package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //private Handler handler;

    boolean isPlayingMenuTheme = false;
    boolean isPlayingTheme = false;
    boolean isPlayingSword = false;

    boolean wasStarted;
    boolean isMenu;

    MediaPlayer mpMenu;
    MediaPlayer mpSword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();
        layoutLandscape();

        menu();
    }

    public void layoutLandscape(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void menu(){
        isMenu = true;

        menuSoundtrack();
    }

    public void menuSoundtrack(){
        mpMenu = MediaPlayer.create(this, R.raw.menu);

        mpMenu.setLooping(isMenu);
        mpMenu.start();
    }

    public void startBtn(View v){
        isMenu = false;
        wasStarted = true;

        mpMenu.stop();

        swordSound();
        startGame();
    }

    public void swordSound(){
        mpSword = MediaPlayer.create(this, R.raw.espada);

        mpSword.start();
    }

    public void startGame(){
        //startSoundtrack();
    }


/*
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
*/
}