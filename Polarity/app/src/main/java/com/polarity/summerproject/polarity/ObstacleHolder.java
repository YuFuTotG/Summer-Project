package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import java.util.Random;


public class ObstacleHolder {
    Obstacle[] obsHolder;
    Player player;
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
    public void moveObsDown() {
        for (int i = 0; i < this.rows*this.cols; i++){
            obsHolder[i].moveY();
        }
    }

    public void colCheck(){
        // for obstacle
        for (int i = 0; i < this.rows*this.cols; i++){
            if (this.obsHolder[i].y > this.screen.getHeight()){
                // move back to the top
                obsHolder[i].y -= rows*obsHolder[i].size;
                obsHolder[i].color = genColor();
            }
        }
        // for player
    }

    public void playerInit(Context context){
        player = new Player(context, 160, 200, this.screen.getWidth() - 200, this.screen.getHeight() - 300);
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
        this.player.image.setBounds(this.player.x,
                this.player.y,
                this.player.x + this.player.width,
                this.player.y + this.player.height);
        this.player.image.draw(canvas);
    }
}
