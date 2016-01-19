package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class ObstacleHolder {
    Obstacle[] obsHolder;
    Player player;
    //DirectionPad dirPad;
    PolarityButton btn;
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
        // for obstacle at bottom of screen
        for (int i = 0; i < this.rows*this.cols; i++){
            if (this.obsHolder[i].y > this.screen.getHeight()){
                // move back to the top
                obsHolder[i].y -= rows*obsHolder[i].size;
                obsHolder[i].color = genColor();
            }
        }
        // for player
        if (player.x < sideBoarderSize){    // side control
            player.x = sideBoarderSize;
        }else if (player.x + player.width > screen.getWidth() - sideBoarderSize){
            player.x = -(int)player.width + screen.getWidth() - sideBoarderSize;
        }
        if (player.y < -player.height){  // top boarder control
            player.moveDown();
        }
        // check for game over
        if (player.y + player.height > screen.getHeight()){
            // game over
            return true;
        }
        return colourColCheck();
    }
    // TODO: make this more efficient
    public boolean colourColCheck(){ // checks if player is in wrong colour
        for(int i = 0; i < obsHolder.length; i++){
            if(obsHolder[i].x + obsSize/2 >= player.x
                    && obsHolder[i].x + obsSize/2 <= player.x + player.width
                    && obsHolder[i].y  + obsSize/2 >= player.y
                    && obsHolder[i].y  + obsSize/2 <= player.y + player.height){
                if(obsHolder[i].color != player.getColor()){
                    return true;
                }else{ return false; }
            }
        }

        return false;
    }

    public void playerInit(Context context){
        player = new Player(context, obsSize, obsSize,
                this.screen.getWidth() - sideBoarderSize - obsSize*((int)(cols/2)),
                this.screen.getHeight() - topBoarderSize - obsSize*((int)(rows/2)), speed);
        for (int i = 0; i < this.rows*this.cols; i++){
            if (obsHolder[i].y == player.y && obsHolder[i].x == player.x){
                // set player color

            }
        }
        //dirPad = new DirectionPad(context, obsSize * 2, obsSize * 2,
        //        sideBoarderSize, screen.getHeight() - obsSize * 2 - topBoarderSize);
        btn = new PolarityButton(context, (int)(obsSize * 1.5), (int)(obsSize * 1.5),
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
        //this.dirPad.draw(canvas);
        this.btn.draw(canvas);
    }
    // TODO: make this clearer. enumeration?
    public void playerChange(boolean white){
        player.change = white;
        // handle button change
        if(white)btn.changeColor(Colour.WHITE);
        else btn.changeColor(Colour.BLACK);
    }

    public void playerMove(Dir dir){
        switch(dir){
            case UP:
                player.moveUp();
                return;
            case DOWN:
                player.moveDown();
                return;
            case LEFT:
                player.moveLeft();
                return;
            case RIGHT:
                player.moveRight();
                return;
            default:
                return;
        }
    }

    public void speedUp(){
        if(player.speed >= Game.MAX_SPEED) return;
        player.speedUp(Game.SPEED_INTERVAL);
        for(int i = 0; i < obsHolder.length; i++){
            obsHolder[i].speedUp(Game.SPEED_INTERVAL);
        }
    }
}
