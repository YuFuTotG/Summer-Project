package com.polarity.summerproject.polarity;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class Player extends Piece{
    int speed, imageIndex;
    public Drawable[] image;
    boolean change;

    public Player(Context context,int height, int width, int x, int y, int speed){
        super(context,height, width, x, y);

        this.speed = speed;
        this.image = new Drawable[3];
        this.image[0] = context.getDrawable(R.drawable.white_pacman_ghost);
        this.image[1] = context.getDrawable(R.drawable.black_pacman_ghost);
        this.image[2] = context.getDrawable(R.drawable.special_pacman_ghost);
        this.imageIndex = 1;
        this.change = false;
    }

    public int getColor(){
        if(imageIndex == 0) return Color.WHITE;
        return Color.BLACK;
    }

    public void polarity(){
        /*
        if(this.change == true && imageIndex == 1 ||
                (this.change == false && imageIndex == 0)) Game.score+=Game.SCORE;
                */
        if(this.change == true){
            imageIndex = 0;
            return;
        }
        imageIndex = 1;
    }

    public void move(){
        this.y = this.y + speed;
    }

    public void moveUp(){
        this.y -= height;
        polarity();
    }
    public void moveDown(){
        this.y += height;
        polarity();
    }
    public void moveLeft(){
        this.x -= width;
        polarity();
    }
    public void moveRight(){
        this.x += width;
        polarity();
    }

    public void draw(Canvas canvas){
        image[imageIndex].setBounds(x, y, x + (int)width, y + (int)height);
        image[imageIndex].draw(canvas);
    }

    public void speedUp(int speed){ this.speed += speed; }
}
