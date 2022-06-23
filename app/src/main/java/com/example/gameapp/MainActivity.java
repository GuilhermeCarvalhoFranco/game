package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
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
    private boolean action;

    boolean isSave = true;
    boolean wasStarted;
    boolean isMenu;
    boolean atk;


    float startPosition;

    ConstraintLayout cl;

    MediaPlayer mpMenu;
    MediaPlayer mpSword;
    MediaPlayer mpTheme;

    Button btnStart, btnLeft, btnRight, btnAtack;
    ImageButton btnMenu;
    ImageView imgSamurai;

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
        btnAtack = findViewById(R.id.btnAtack);

        imgSamurai = (ImageView) findViewById(R.id.imgSamurai);

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

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_corrida));
                    Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
                    animation.start();

                    params.width = 280;
                    params.height = 192;

                    Log.d(TAG, "onTouch: "+imgSamurai.getWidth()+"|"+imgSamurai.getHeight());
                    imgSamurai.setScaleType(ImageView.ScaleType.FIT_START);
                    imgSamurai.setLayoutParams(params);

                    imgSamurai.setRotationY(0);
                }

                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_samurai));
                    Animatable anime = (AnimationDrawable) imgSamurai.getDrawable();
                    anime.start();

                    params.width = 280;
                    params.height = 192;
                    imgSamurai.setScaleType(ImageView.ScaleType.FIT_START);
                    imgSamurai.setLayoutParams(params);
                }

                return false;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_corrida));
                    Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
                    animation.start();


                    params.width = 280;
                    params.height = 192;

                    Log.d(TAG, "onTouch: "+imgSamurai.getWidth()+"|"+imgSamurai.getHeight());
                    imgSamurai.setScaleType(ImageView.ScaleType.FIT_START);
                    imgSamurai.setLayoutParams(params);

                    imgSamurai.setRotationY(180);
                }

                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_samurai));
                    Animatable anime = (AnimationDrawable) imgSamurai.getDrawable();
                    anime.start();


                    params.width = 280;
                    params.height = 192;

                    Log.d(TAG, "onTouch: "+imgSamurai.getWidth()+"|"+imgSamurai.getHeight());
                    imgSamurai.setScaleType(ImageView.ScaleType.FIT_START);
                    imgSamurai.setLayoutParams(params);
                }

                return false;
            }
        });

        action = false;
        verifica();
    }

    public void attack(){
        if(atk){
            imgSamurai.setImageDrawable(getDrawable(R.drawable.list_attack_start));
            Animatable anime = (AnimationDrawable) imgSamurai.getDrawable();
            anime.start();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imgSamurai.getLayoutParams();
                    params.width = 350;
                    params.height = 280;
                    imgSamurai.setLayoutParams(params);

                    imgSamurai.setScaleType(ImageView.ScaleType.FIT_XY);

                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_attack_end));
                    Animatable anime = (AnimationDrawable) imgSamurai.getDrawable();
                    anime.start();
                }
            }, 200);

            atk = false;
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imgSamurai.getLayoutParams();
                params.width = 280;
                params.height = 192;

                imgSamurai.setScaleType(ImageView.ScaleType.FIT_START);
                imgSamurai.setLayoutParams(params);

                if(btnLeft.isPressed() || btnRight.isPressed()){
                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_corrida));
                    Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
                    animation.start();
                }else{
                    imgSamurai.setImageDrawable(getDrawable(R.drawable.list_samurai));
                    Animatable anime = (AnimationDrawable) imgSamurai.getDrawable();
                    anime.start();
                }
            }
        }, 400);
    }

    public void verifica() {
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

    public void move(int direction) {
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
        btnAtack.setVisibility(View.INVISIBLE);


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

        imgSamurai.setX(startPosition);

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
    }

    public void createSamurai(){
        imgSamurai.setVisibility(View.VISIBLE);
        imgSamurai.setImageDrawable(getDrawable(R.drawable.list_samurai));

        Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
        animation.start();

        if(isSave){
            startPosition = imgSamurai.getX();

            isSave = false;
        }else{
            imgSamurai.setX(startPosition);
        }
    }

    public void createButtons(){
        btnRight.setVisibility(View.VISIBLE);
        btnLeft.setVisibility(View.VISIBLE);
        btnAtack.setVisibility(View.VISIBLE);
    }

}

