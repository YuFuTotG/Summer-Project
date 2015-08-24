package com.polarity.summerproject.polarity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Game{
    //int testX, testY, sizeX, sizeY;
    Paint paint;
    Paint bg;
    Screen screen;

    public Game(Screen screen) {
        paint = new Paint();
        bg = new Paint();
        bg.setColor(Color.BLACK);
        paint.setColor(Color.WHITE);

        this.screen = screen;
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        // draw bg first
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getWidth(), bg);
        canvas.drawRect(0,0, screen.getWidth(), screen.getHeight(), paint);
    }

    public void onTouch(MotionEvent me){
        if (me.getX() < screen.getWidth()/2){
            paint.setColor(Color.RED);
        }else{
            paint.setColor(Color.WHITE);
        }
    }
}
