package com.polarity.summerproject.polarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Created by Malzberry on 7/5/2015.
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    Paint paint;
    SurfaceHolder ourHolder;
    public Game(Context context) {
        super(context);

        ourHolder = getHolder();
        ourHolder.addCallback(this);
        this.run();
    }
    public void run(){
        paint = new Paint();

        paint.setColor(Color.BLACK);
        Canvas canvas = ourHolder.lockCanvas();
        canvas.drawRect(0, 0, 200, 200, paint);


        /*
        //while(true) {

            testText.setText("!IT WORKED!");
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
            testText.setText("!!IT WORKED!!");
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
            testText.setText("!!!IT WORKED!!!");
        //}
        */

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceDestroyed(SurfaceHolder arg0) {

    }

}
