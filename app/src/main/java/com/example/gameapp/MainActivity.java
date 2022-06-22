package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    Button btnStart;
    ImageButton btnMenu;
    ImageView imgSamurai;


    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();
        btnStart = findViewById(R.id.btnStart);
        btnMenu = findViewById(R.id.btnMenu);
        imgSamurai = (ImageView) findViewById(R.id.imgSamurai);





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

    }

    public void layoutLandscape(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void menu(){
        isMenu = true;
        wasStarted = false;
        
        imgSamurai.setVisibility(View.INVISIBLE);


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

       /* Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);

        setContentView(gameView);*/

    }


    public void themeSoundtrack(){
        mpTheme = MediaPlayer.create(this, R.raw.theme);

        mpTheme.setLooping(wasStarted);
        mpTheme.start();
    }

    public void menuFlutuante(){
        swordSound();

        if(isMenu) {
            mpMenu.stop();
        }else{
            mpTheme.stop();
            btnStart.setVisibility(View.VISIBLE);
        }

        menu();
        layoutLandscape();
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
        createSamurai();
    }

    public void createSamurai(){
        imgSamurai.setVisibility(View.VISIBLE);

        Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
        animation.start();
    }

}

