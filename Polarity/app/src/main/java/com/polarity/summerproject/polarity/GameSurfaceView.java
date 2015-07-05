package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable{
    private boolean isRunning = false;
    private Thread gameThread;
    private SurfaceHolder holder;
    Paint paint;

    public GameSurfaceView(Context context) {
        super(context);
        paint = new Paint();

        holder = getHolder();
        // Here we can handle additional surface notifications (i.e. created, destroyed, etc.)
        //holder.addCallBack();
    }

    @Override
    public void run(){
        paint.setColor(Color.WHITE);
        while(isRunning) {
            // need to make sure surface is ready
            if (! holder.getSurface().isValid()) {
                continue;
            }
            // UPDATE GAME HERE
            // game.update();
            // game.step();

            // DRAW
            Canvas canvas = holder.lockCanvas();
            if(canvas != null) {
                // game.draw();
                // canvas.draw(...);
                canvas.drawRect(0,0,200,200, paint);
                holder.unlockCanvasAndPost(canvas);
            }

        }

    }

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
