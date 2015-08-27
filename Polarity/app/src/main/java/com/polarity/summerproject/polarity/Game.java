package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Game{
    //int testX, testY, sizeX, sizeY;
    Paint paint;
    Paint bg;
    Screen screen;
    ObstacleHolder obsHolder;
    Context context;

    public Game(Screen screen) {
        paint = new Paint();
        bg = new Paint();
        bg.setColor(Color.BLACK);
        paint.setColor(Color.WHITE);

        this.screen = screen;
        obsHolder = new ObstacleHolder(this.screen, 200, 8);   // ENTER OBSTACLE SIZE AND SPEED HERE.

    }

    public boolean update(){
        obsHolder.moveObj();
        if (obsHolder.colCheck() == true){
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas){    // DRAW OBSTACLE IN ITS OWN FUNCTION? probably
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), bg);       // draw bg

        obsHolder.draw(canvas);
    }

    public void onTouch(MotionEvent me){
        // when pressed down
        obsHolder.playerMove((int) me.getX(), (int) me.getY());
    }

    public void obsHolderInit(){
        this.obsHolder.playerInit(this.context);
    }

    public void setContext(Context context){
        this.context = context;
    }
}
