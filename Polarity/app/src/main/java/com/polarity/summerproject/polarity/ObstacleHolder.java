package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import java.util.Random;

import static java.lang.Math.abs;


public class ObstacleHolder {
    Obstacle[] obsHolder;
    Player player;
    DirectionPad dirPad;
    Screen screen;
    int rows, cols, obsSize, sideBoarderSize, topBoarderSize, speed;

    public ObstacleHolder(Screen screen,int size, int speed){
        this.screen = screen;
        this.speed = speed;
        this.obsSize = size;
        this.rows = (int) (screen.getHeight())/size + 2;
        this.cols = (int) (screen.getWidth())/size;
        this.sideBoarderSize = (screen.getWidth()%size + 1)/2;
        this.topBoarderSize = (screen.getHeight()%size + 1)/2;
        this.obsHolder = new Obstacle[rows*cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                obsHolder[j + cols*i] = new Obstacle(genColor(), obsSize, j*obsSize + sideBoarderSize, i*obsSize/* + topBoarderSize*/ - screen.getHeight(), this.speed);
            }
        }
    }
    private int genColor(){
        Random rand = new Random();
        if (rand.nextInt(10)%2 == 1) {
            return Color.WHITE;
        }else{
            return Color.BLACK;
        }
    }
    public void moveObj() {
        // obstacles
        for (int i = 0; i < this.rows*this.cols; i++){
            obsHolder[i].moveY();
        }
        // player
        player.move();
    }

    public boolean colCheck(){
        // for obstacle
        for (int i = 0; i < this.rows*this.cols; i++){
            if (this.obsHolder[i].y > this.screen.getHeight()){
                // move back to the top
                obsHolder[i].y -= rows*obsHolder[i].size;
                obsHolder[i].color = genColor();
            }
        }
        // for player
        if (player.x < sideBoarderSize){
            player.x = sideBoarderSize;
        }else if (player.x + player.width > screen.getWidth() - sideBoarderSize){
            player.x = -player.width + screen.getWidth() - sideBoarderSize;
        }
        // check for game over
        if (player.y + player.height > screen.getHeight()){
            // game over
            return true;
        }
        return false;
    }

    public void gameOver(){

    }

    public void playerInit(Context context){
        player = new Player(context, obsSize, obsSize,
                this.screen.getWidth() - sideBoarderSize - obsSize*((int)(cols/2)),
                this.screen.getHeight() - topBoarderSize - obsSize*((int)(rows/2)), speed);
        dirPad = new DirectionPad(context, obsSize * 2, obsSize * 2,
                sideBoarderSize, screen.getHeight() - obsSize * 2 - topBoarderSize);
    }

    public void draw(Canvas canvas){
        // draw obstacles
        Paint paint = new Paint();
        for(int i = 0; i < obsHolder.length; i++){
            paint.setColor(obsHolder[i].color);
            canvas.drawRect(obsHolder[i].x,
                    obsHolder[i].y,
                    obsHolder[i].x + obsHolder[i].size,
                    obsHolder[i].y + obsHolder[i].size, paint);
        }
        // draw player
        this.player.draw(canvas);
        this.dirPad.draw(canvas);
    }

    public void playerMove(int x, int y){
        // collision check here dirpad
        if (x < dirPad.width && y > dirPad.y) {
            // top test y
            if (y < dirPad.y + dirPad.height/3){
                player.moveUp();
            }else if (y < dirPad.y + dirPad.height/3*2) {
                // middle test left or right
                if (x < dirPad.width/2){
                    player.moveLeft();
                }else{
                    player.moveRight();
                }
            }else {
                // bottom
                player.moveDown();
            }
        }
    }
}
