package com.polarity.summerproject.polarity;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Player {
    int height, width, x, y, speed;
    public Drawable image;

    public Player(Context context,int height, int width, int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;

        this.image = context.getDrawable(R.drawable.white_pacman_ghost);
    }

    public void move(){
        this.y = this.y + speed;
    }

    public void moveUp(){
        this.y -= height;
    }
    public void moveDown(){
        this.y += height;
    }
    public void moveLeft(){
        this.x -= width;
    }
    public void moveRight(){
        this.x += width;
    }

    public void draw(Canvas canvas){
        image.setBounds(x, y, x + width, y + height);
        image.draw(canvas);
    }
}
