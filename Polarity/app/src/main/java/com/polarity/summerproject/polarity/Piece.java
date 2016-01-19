package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Malzberry on 1/14/2016.
 */
public class Piece {
    public Drawable image;
    public int  x, y;
    public float height, width;
    public Piece(Context context, float height, float width, int x, int y){
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        this.image = context.getDrawable(R.drawable.direction_pad);
    }

    public void draw(Canvas canvas){
        image.setBounds(x, y, x + (int) width, y + (int) height);
        image.draw(canvas);
    }
}
