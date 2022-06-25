package com.example.gameapp;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
    Context context;
    MediaPlayer music;

    public void MyClass(Context context) {
        this.context = context;
    }

    public MediaPlayer musicMenuGame(boolean menu, boolean game){

        if(menu){
            music = MediaPlayer.create(context, R.raw.menu);
        }else{
            music = MediaPlayer.create(context, R.raw.theme);
        }
        music.setLooping(true);
        return music;
    }

    public void swordSound(){
        MediaPlayer mpSword;
        mpSword = MediaPlayer.create(context, R.raw.espada);

        mpSword.start();
    }
}