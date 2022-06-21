package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    //private Handler handler;

    boolean isPlayingMenuTheme = false;
    boolean isPlayingTheme = false;
    boolean isPlayingSword = false;

    boolean wasStarted;
    boolean isMenu;

    MediaPlayer mpMenu;
    MediaPlayer mpSword;
    MediaPlayer mpTheme;

    Button btnStart, btnSair;
    ImageButton btnMenu;

    FrameLayout frMenu;

    int contMenu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();
        btnStart = findViewById(R.id.btnStart);
        btnMenu = findViewById(R.id.btnMenu);
        btnSair = findViewById(R.id.btnSair);
        frMenu = findViewById(R.id.frameMenu);

        frMenu.setVisibility(View.INVISIBLE);


        layoutLandscape();

        menu();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuFlutuante();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMenu){
                    mpMenu.stop();
                }

                if(wasStarted){
                    mpTheme.stop();
                }

                menu();

                contMenu = 0;
                frMenu.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.VISIBLE);
            }
        });
    }

    public void layoutLandscape(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void menu(){
        isMenu = true;
        wasStarted = false;


        menuSoundtrack();
    }

    public void menuSoundtrack(){
        mpMenu = MediaPlayer.create(this, R.raw.menu);

        mpMenu.setLooping(isMenu);
        mpMenu.start();
    }

    public void swordSound(){
        mpSword = MediaPlayer.create(this, R.raw.espada);

        mpSword.start();
    }

    public void start(){
        isMenu = false;
        wasStarted = true;

        mpMenu.stop();

        swordSound();

        startGame();
    }



    public void startGame(){
        btnStart.setVisibility(View.INVISIBLE);

        themeSoundtrack();

    }


    public void themeSoundtrack(){
        mpTheme = MediaPlayer.create(this, R.raw.theme);

        mpTheme.setLooping(wasStarted);
        mpTheme.start();
    }

    public void menuFlutuante(){
        swordSound();

        if(isMenu) {
            if (contMenu == 0) {
                frMenu.setVisibility(View.VISIBLE);

                btnStart.setVisibility(View.INVISIBLE);

                contMenu = 1;
            } else {
                frMenu.setVisibility(View.INVISIBLE);

                btnStart.setVisibility(View.VISIBLE);

                contMenu = 0;
            }
        }else{
            if (contMenu == 0) {
                frMenu.setVisibility(View.VISIBLE);

                contMenu = 1;
            } else {
                frMenu.setVisibility(View.INVISIBLE);

                contMenu = 0;
            }
        }

    }

}