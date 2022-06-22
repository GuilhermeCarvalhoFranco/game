package com.example.gameapp;

import static com.example.gameapp.GameView.screenRatioX;
import static com.example.gameapp.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Personagem {

    public boolean isGoinRight = false;
    int x, y, width, height, countPath = 0;
    Bitmap run, idle;

    Personagem(int screenX, Resources res){
        //idle = BitmapFactory.decodeResource(res, R.drawable.iidle);
        //run = BitmapFactory.decodeResource(res, R.drawable.run);

        width = idle.getWidth();
        height = idle.getHeight();

        //width /= 4;
        //height /= 4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        idle = Bitmap.createScaledBitmap(idle, width, height, false);
        run = Bitmap.createScaledBitmap(run, width, height, false);

        x = screenX / 2;
        y = (int) (64 * screenRatioY);
    }

    Bitmap getPersonagem(){
        if(countPath == 0){
            countPath++;
            return idle;
        }
        countPath--;

        return run;
    }
}
