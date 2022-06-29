package com.example.gameapp;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Goblin {
    Context context;

    public void MyClass(Context context) {
        this.context = context;
    }

    public void corrida(ImageView imgGoblin, ConstraintLayout cl){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int spc = 15;
                int dist = cl.getWidth() - 115 - spc;

                while (true){
                    imgGoblin.setX(dist);
                    dist = cl.getWidth() - 115 - spc;
                    spc -= 15;
                }
            }
        }).start();
    }
}
