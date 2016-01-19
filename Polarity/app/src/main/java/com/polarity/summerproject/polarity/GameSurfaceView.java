package com.polarity.summerproject.polarity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameSurfaceView extends SurfaceView implements Runnable{
    private boolean isRunning = false;
    private Thread gameThread;
    private SurfaceHolder holder;
    Paint paint;
    Game game;

    //desitred fps
    private final static int MAX_FPS = 25;
    // max number of frames to be skipped
    private final static int MAX_FRAME_SKIPS = 5;
    // frame period in milli
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;


    public GameSurfaceView(Context context, Game game) {
        super(context);
        paint = new Paint();
        this.game = game;

        holder = getHolder();
        // Here we can handle additional surface notifications (i.e. created, destroyed, etc.)
        //holder.addCallBack();
    }

    @Override
    public void run(){
        paint.setColor(Color.WHITE);
        Canvas  canvas;

        long beginTime;     // time when cycle begins
        long timeDiff;      // time for cycle to execute
        int sleepTime;      // time to sleep (<0 ms if behind)
        int framesSkipped;  // number of frames skipped

        sleepTime = 0;

        while(isRunning) {
            canvas = null;
            // need to make sure surface is ready
            if (! holder.getSurface().isValid()) {
                continue;
            }

            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;  // reset frames skipped

                    // UPDATE GAME HERE
                    if (game.update() == true){
                        isRunning = false;

                        Context context = getContext();

                        SharedPreferences sp = context.getSharedPreferences("game", 0);
                        SharedPreferences.Editor spEditor = sp.edit();

                        if(sp.contains("highScore")){
                            if(sp.getInt("highScore", 0) < Game.score){
                                // TODO: make a try?
                                // replace
                                spEditor.putInt("highScore", Game.score);
                                spEditor.commit(); // apply()?
                            }
                        }else{
                            spEditor.putInt("highScore", Game.score);
                            spEditor.commit(); // apply()?
                        }


                        ((GameActivity) context).finish();
                    }

                    // DRAW
                    game.draw(canvas);

                    // FRAME RATE CONTROL
                    timeDiff = System.currentTimeMillis() - beginTime;
                    // calculate sleep time
                    sleepTime = (int)(FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0) {    // we're ahead of the frame time
                        try {
                            // send thread to sleep
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {}
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                        // we need to catch up here
                        // update without drawing
                        game.update();
                        // add frames period to check if in next frame
                        sleepTime += FRAME_PERIOD;
                        framesSkipped++;
                    }
                }
            } finally {
                // in case of exception
                if(canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
        // go back to main page or game over activity.
    }
    // inflate view



    public void resume(){
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause(){
        isRunning = false;
        boolean retry = true;
        while( retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e){
                // try again shutting down the thread
            }
        }
    }
}
