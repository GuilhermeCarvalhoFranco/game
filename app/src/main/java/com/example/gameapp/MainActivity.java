package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private static final String TAG = "APP";

    boolean isPlayingMenuTheme = false;
    boolean isPlayingTheme = false;
    boolean isPlayingSword = false;
    boolean isAnimating = false;


    boolean wasStarted;
    boolean isMenu;

    ConstraintLayout cl;

    //private ImageView viewBack = findViewById(R.id.imageView);
    //View BackView = findViewById(R.id.imgBack);
    //int largura = ;

    MediaPlayer mpMenu;
    MediaPlayer mpSword;
    MediaPlayer mpTheme;

    Button btnStart, btnLeft, btnRight;
    ImageButton btnMenu;
    ImageView imgSamurai;


    GameView gameView;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        btnStart = findViewById(R.id.btnStart);
        btnMenu = findViewById(R.id.btnMenu);
        btnRight = findViewById(R.id.btnRight);
        btnLeft = findViewById(R.id.btnLeft);

        imgSamurai = (ImageView) findViewById(R.id.imgSamurai);

        cl = findViewById(R.id.clGame);



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

        btnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                imgSamurai.setImageDrawable(getDrawable(R.drawable.list_corrida));
                Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
                animation.start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(btnRight.isPressed()){
                            moveToRight();

                            try {
                                Thread.sleep(70);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                return false;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                imgSamurai.setImageDrawable(getDrawable(R.drawable.list_corrida));
                Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
                animation.start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(btnLeft.isPressed()){
                            moveToLeft();

                            try {
                                Thread.sleep(70);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                return false;
            }
        });
    }

    public void moveToLeft(){
            float x = imgSamurai.getX();
            x = x-25;


            if(x < 0){
                x = 0;
                imgSamurai.setX(x);
            }else{
                imgSamurai.setX(x);
            }

    }

    public void moveToRight(){
        float x = imgSamurai.getX();
        x = x+25;

        int largura = cl.getWidth() - 200;

        if(x > largura){
            x = largura;
            imgSamurai.setX(x);
        }else{
            imgSamurai.setX(x);
        }
    }


    public void layoutLandscape(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void menu(){
        isMenu = true;
        wasStarted = false;
        
        imgSamurai.setVisibility(View.INVISIBLE);
        btnRight.setVisibility(View.INVISIBLE);
        btnLeft.setVisibility(View.INVISIBLE);


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
        createButtons();

        /*Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView = new GameView(this, point.x, point.y);
        gameView = new GameView(MainActivity.this, point.x, point.y);

        setContentView(gameView);*/
    }

    public void createSamurai(){
        imgSamurai.setVisibility(View.VISIBLE);
        imgSamurai.setImageDrawable(getDrawable(R.drawable.list_samurai));

        Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
        animation.start();
    }

    public void createButtons(){
        btnRight.setVisibility(View.VISIBLE);
        btnLeft.setVisibility(View.VISIBLE);
    }

}

