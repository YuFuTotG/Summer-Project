package com.polarity.summerproject.polarity;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Player {
    int height, width, x, y;
    public Drawable image;
    //public Bitmap image;

    public Player(Context context,int height, int width, int x, int y){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        this.image = context.getDrawable(R.drawable.white_ghost);
    }
}
