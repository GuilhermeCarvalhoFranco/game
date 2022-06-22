package com.example.gameapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameView extends SurfaceView  implements Runnable {
    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Personagem personagem;
    private Paint paint;
    private Button btnR = findViewById(R.id.btnRight);
    private Button btnL = findViewById(R.id.btnLeft);

    public GameView(Context context, int screenX, int screenY){
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        personagem = new Personagem(screenX, getResources());
/*
        btnR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if(view.getY() < screenY / 2){
                    personagem.isGoinRight = true;
                }
            }
        });

        btnL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                personagem.isGoinRight = false;
            }
        });
*/
    }

    public void right(View view){
        if(view.getY() < screenY / 2){
            personagem.isGoinRight = true;
        }
    }


    @Override
    public void run() {

        while(isPlaying){
            update();
            draw();
            sleep();
        }

    }

    private void update() {

    }

    private void draw() {
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();

            canvas.drawBitmap(personagem.getPersonagem(), personagem.x, personagem.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

        if(personagem.isGoinRight){
            personagem.x -= 30 * screenRatioX;
        }else{
            personagem.x -= 30 * screenRatioX;
        }

        if (personagem.x < 0){
            personagem.x = 0;
        }

        if(personagem.x < screenX - personagem.width){
            personagem.x = screenX - personagem.width;
        }
    }


    private void sleep() {

        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

  /*  public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 */
    }

    //Função para botões

