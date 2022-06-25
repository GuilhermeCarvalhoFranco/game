package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private static final String TAG = "APP";
    public final static int MOVE_LEFT = 0, MOVE_RIGHT = 1;
    public final static int ORI_LEFT = 180, ORI_RIGHT = 0;

    boolean action;
    boolean wasStarted;
    boolean isMenu;
    boolean atk;
    boolean savePosition = false;

    int startPosition;

    ConstraintLayout cl;

    MediaPlayer mpMenu;
    MediaPlayer mpGame;

    Button btnStart, btnLeft, btnRight, btnAtack;
    ImageButton btnMenu;
    ImageView imgSamurai, imgGoblin;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation anima = new Animation();
        anima.MyClass(MainActivity.this);

        Sound music = new Sound();
        music.MyClass(MainActivity.this);

        handler = new Handler();
        btnStart = findViewById(R.id.btnStart);
        btnMenu = findViewById(R.id.btnMenu);
        btnRight = findViewById(R.id.btnRight);
        btnLeft = findViewById(R.id.btnLeft);
        btnAtack = findViewById(R.id.btnAtack);

        imgSamurai = (ImageView) findViewById(R.id.imgSamurai);
        imgGoblin = (ImageView) findViewById(R.id.imgGoblin);

        cl = findViewById(R.id.clGame);

        layoutLandscape();

        menu();

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imgSamurai.getLayoutParams();

        btnAtack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atk = true;
                attack();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
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
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    anima.samuraiRunAnimation(imgSamurai, ORI_RIGHT);
                }

                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    anima.samuraiIdleAnimation(imgSamurai);
                }

                return false;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    anima.samuraiRunAnimation(imgSamurai, ORI_LEFT);
                }

                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    anima.samuraiIdleAnimation(imgSamurai);
                }

                return false;
            }
        });

        action = false;
        verifica();

        imgGoblin.setImageDrawable(getDrawable(R.drawable.list_gorrida_gob));
        Animatable animation = (AnimationDrawable) imgGoblin.getDrawable();
        animation.start();
    }

    private void attack(){
        Animation anima = new Animation();
        anima.MyClass(MainActivity.this);

        if(atk){
            atk = false;

            anima.samuraiAttackStartAnimation(imgSamurai);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    anima.samuraiAttackEndAnimation(imgSamurai);
                }
            }, 250);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(btnLeft.isPressed() || btnRight.isPressed()){
                        if(btnLeft.isPressed()){
                            anima.samuraiRunAnimation(imgSamurai, ORI_LEFT);
                        }else{
                            anima.samuraiRunAnimation(imgSamurai, ORI_RIGHT);
                        }
                    }else{
                        anima.samuraiIdleAnimation(imgSamurai);
                    }
                }
            }, 400);
        }
    }

    private void verifica() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(btnLeft.isPressed())
                        move(MOVE_LEFT);
                    else if(btnRight.isPressed())
                        move(MOVE_RIGHT);
                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void move(int direction) {
        float inc = 25;
        if(direction == MOVE_LEFT)
            inc *= -1;

        float x = imgSamurai.getX();
        x += inc;

        int larguraMax = cl.getWidth() - 200;

        if(x < 0)
            x = 0;
        else if (x > larguraMax)
            x = larguraMax;

        imgSamurai.setX(x);
    }

    private void layoutLandscape(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void menu(){
        isMenu = true;
        wasStarted = false;

        menuMusic();
        hideGame();
    }

    private void menuFlutuante(){
        swordSound();

        if(isMenu) {
            mpMenu.stop();
        }else{
            mpGame.stop();
            btnStart.setVisibility(View.VISIBLE);
        }

        menu();
        layoutLandscape();
    }

    private void swordSound(){
        Sound music = new Sound();
        music.MyClass(MainActivity.this);

        music.swordSound();
    }

    private void startGame(){
        isMenu = false;
        wasStarted = true;

        swordSound();
        showGame();
        gameMusic();
    }

    private void hideGame(){
        imgSamurai.setVisibility(View.INVISIBLE);
        btnRight.setVisibility(View.INVISIBLE);
        btnLeft.setVisibility(View.INVISIBLE);
        btnAtack.setVisibility(View.INVISIBLE);
    }

    private void showGame(){
        btnStart.setVisibility(View.INVISIBLE);

        showSamurai();
        showButtons();
    }

    private void showSamurai(){
        imgSamurai.setVisibility(View.VISIBLE);
        imgSamurai.setRotationX(ORI_RIGHT);

        Animation anima = new Animation();
        anima.MyClass(MainActivity.this);

        anima.samuraiIdleAnimation(imgSamurai);
        if(!savePosition){
            startPosition = (cl.getWidth() - 100) /2;
            savePosition = true;
        }else{
            imgSamurai.setX(startPosition);
        }
    }

    private void showButtons(){
        btnRight.setVisibility(View.VISIBLE);
        btnLeft.setVisibility(View.VISIBLE);
        btnAtack.setVisibility(View.VISIBLE);
    }

    private void menuMusic(){
        Sound music = new Sound();
        music.MyClass(MainActivity.this);

        mpMenu = music.musicMenuGame(isMenu, wasStarted);
        mpMenu.start();
    }

    private void gameMusic(){
        Sound music = new Sound();
        music.MyClass(MainActivity.this);

        mpMenu.stop();

        mpGame = music.musicMenuGame(isMenu, wasStarted);
        mpGame.start();
    }
}