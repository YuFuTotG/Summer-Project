package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Malzberry on 8/26/2015.
 */
public class DirectionPad {
    public Drawable image;
    public int height, width, x, y;
    public DirectionPad(Context context, int height, int width, int x, int y){
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        this.image = context.getDrawable(R.drawable.direction_pad);
    }

    public void draw(Canvas canvas){
        image.setBounds(x, y, x + width, y + height);
        image.draw(canvas);
    }
}
