package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by Malzberry on 1/14/2016.
 */
public class PolarityButton extends Piece {
    public Drawable[] image;
    int imageIndex;
    public PolarityButton(Context context, int height, int width, int x, int y){
        super(context, height, width, x, y);
        imageIndex = 1;
        image = new Drawable[2];
        this.image[0] = context.getDrawable(R.drawable.white_ghost);
        this.image[1] = context.getDrawable(R.drawable.black_ghost);
    }
    public void changeColor(Colour color){
        if(color == Colour.WHITE)imageIndex = 0;
        else imageIndex = 1;
    }

    public void draw(Canvas canvas){
        image[imageIndex].setBounds(x, y, x + (int)width, y + (int)height);
        image[imageIndex].draw(canvas);
    }
}
