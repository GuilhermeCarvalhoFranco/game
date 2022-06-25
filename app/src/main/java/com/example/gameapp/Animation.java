package com.example.gameapp;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Animation{
    ConstraintLayout.LayoutParams params;
    Context context;
    Drawable draw;

    public void MyClass(Context context) {
        this.context = context;
    }

    //========== Samurai Animation
    public void samuraiRunAnimation(ImageView imgSamurai, int orientation){
        imgSamurai.setRotationY(orientation);

        draw = context.getResources().getDrawable(R.drawable.list_corrida);
        samuraiMediumSize(imgSamurai);
        samuraiStartAnimation(imgSamurai, draw);
    }

    public void samuraiIdleAnimation(ImageView imgSamurai){
        draw = context.getResources().getDrawable(R.drawable.list_samurai);
        samuraiMediumSize(imgSamurai);
        samuraiStartAnimation(imgSamurai, draw);
    }

    public void samuraiAttackStartAnimation(ImageView imgSamurai){
        draw = context.getResources().getDrawable(R.drawable.list_attack_start);
        samuraiMediumSize(imgSamurai);
        samuraiStartAnimation(imgSamurai, draw);
    }

    public void samuraiAttackEndAnimation(ImageView imgSamurai){
        draw = context.getResources().getDrawable(R.drawable.list_attack_end);
        samuraiLargeSizeSamurai(imgSamurai);
        samuraiStartAnimation(imgSamurai, draw);
    }

    private void samuraiStartAnimation(ImageView imgSamurai, Drawable animacao){
        imgSamurai.setImageDrawable(animacao);
        Animatable animation = (AnimationDrawable) imgSamurai.getDrawable();
        animation.start();
    }

    private void samuraiMediumSize(ImageView imgSamurai){
        params = (ConstraintLayout.LayoutParams) imgSamurai.getLayoutParams();
        params.width = 280;
        params.height = 192;

        imgSamurai.setScaleType(ImageView.ScaleType.FIT_START);
        imgSamurai.setLayoutParams(params);
    }

    private void samuraiLargeSizeSamurai(ImageView imgSamurai){
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) imgSamurai.getLayoutParams();
        params.width = 350;
        params.height = 280;
        imgSamurai.setLayoutParams(params);

        imgSamurai.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    //========== Goblin Animation

}